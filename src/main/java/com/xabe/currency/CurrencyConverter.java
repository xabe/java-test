package com.xabe.currency;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface CurrencyConverter {

    double convert(double amount);

    interface BiFunction {
        Double convert(Double amount, String toCurrency);

        default CurrencyConverter to(String toCurrency) {
            return amount -> convert(amount, toCurrency);
        }
    }

    interface TriFunction {
        Double convert(Double amount, String fromCurrency, String toCurrency);

        default BiFunction from(String fromCurrency) {
            return (amount, toCurrency) -> convert(amount, fromCurrency, toCurrency);
        }
    }

    static TriFunction of(LocalDate date) {

        return (amount, fromCurrency, toCurrency) -> {
            final String path = CurrencyConverter.class.getClassLoader().getResource("data/currency.txt").getPath();
            String decodePath = "";
            try {
                decodePath = URLDecoder.decode(path, StandardCharsets.UTF_8.name());
            }catch (UnsupportedEncodingException e){}
            try (Stream<String> lines = Files.lines(Path.of(decodePath))) {

                Map<String, Double> converterMap =
                        lines.skip(1L)
                                .collect(
                                        Collectors.toMap(
                                                line -> line.substring(0, 3),
                                                line -> Double.parseDouble(line.substring(4))
                                        )
                                );

                return amount*(converterMap.get(toCurrency)/converterMap.get(fromCurrency));

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        };
    }
}
