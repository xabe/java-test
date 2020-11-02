package com.xabe.mapstruct;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import com.xabe.mapstruct.config.ConfigMapStruct;
import com.xabe.mapstruct.dto.CarTypeDTO;
import com.xabe.mapstruct.dto.CardDTO;
import com.xabe.mapstruct.dto.LoanDTO;
import com.xabe.mapstruct.dto.MortgageDTO;
import com.xabe.mapstruct.dto.PayrollAccountDTO;
import com.xabe.mapstruct.dto.SavingAccountDTO;
import com.xabe.mapstruct.entity.CarTypeDO;
import com.xabe.mapstruct.entity.Card;
import com.xabe.mapstruct.entity.Loan;
import com.xabe.mapstruct.entity.Mortgage;
import com.xabe.mapstruct.entity.PayrollAccount;
import com.xabe.mapstruct.entity.SavingAccount;
import com.xabe.mapstruct.mapper.LoanMapper;
import com.xabe.mapstruct.mapper.MortgageMapper;
import com.xabe.mapstruct.mapper.PayrollAccountMapper;
import com.xabe.mapstruct.mapper.SavingAccountMapper;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ConfigMapStruct.class})
public class MapStructTest {

  @Autowired
  private SavingAccountMapper savingAccountMapper;

  @Autowired
  private PayrollAccountMapper payrollAccountMapper;

  @Autowired
  private LoanMapper loanMapper;

  @Autowired
  private MortgageMapper mortgageMapper;

  @Test
  public void givenASavingAccountWheInvokeMapThenReturnSavingAccountDTO() throws Exception {
    final SavingAccount savingAccount = SavingAccount.builder().id("id").name("name").surname("surname").surname2("surname2").birthDate(
        LocalDate.MIN).amount(BigDecimal.TEN).profit(BigDecimal.ONE).build();

    final SavingAccountDTO result = this.savingAccountMapper.toSavingAccountDTO(savingAccount);

    assertThat(result, is(notNullValue()));
    assertThat(result.getId(), is(savingAccount.getId()));
    assertThat(result.getName(), is(savingAccount.getName()));
    assertThat(result.getSurname(), is(savingAccount.getSurname()));
    assertThat(result.getSurname2(), is(savingAccount.getSurname2().get()));
    assertThat(result.getBirthDate(), is(savingAccount.getBirthDate()));
    assertThat(result.getAmount(), is(savingAccount.getAmount()));
    assertThat(result.getProfit(), is(savingAccount.getProfit()));
  }

  @Test
  public void givenAPayrollAccountWheInvokeMapThenReturnPayrollAccountDTO() throws Exception {
    final Card card = Card.builder().id("cardId").expired(LocalDate.MAX).type(CarTypeDO.CREDIT).name("name").build();
    final PayrollAccount payrollAccount = PayrollAccount.builder().id("id").name("name").surname("surname").surname2("surname2").birthDate(
        LocalDate.MIN).amount(BigDecimal.TEN)
        .card(card).build();

    final PayrollAccountDTO result = this.payrollAccountMapper.toPayrollAccountDTO(payrollAccount);

    assertThat(result, is(notNullValue()));
    assertThat(result.getId(), is(payrollAccount.getId()));
    assertThat(result.getName(), is(payrollAccount.getName()));
    assertThat(result.getSurname(), is(payrollAccount.getSurname()));
    assertThat(result.getSurname2(), is(payrollAccount.getSurname2().get()));
    assertThat(result.getBirthDate(), is(payrollAccount.getBirthDate()));
    assertThat(result.getAmount(), is(payrollAccount.getAmount()));
    final CardDTO cardDTO = result.getCard();
    assertThat(cardDTO, is(notNullValue()));
    assertThat(cardDTO.getId(), is(card.getId()));
    assertThat(cardDTO.getName(), is(card.getName()));
    assertThat(cardDTO.getExpired(), is(card.getExpired()));
    assertThat(cardDTO.getType(), is(CarTypeDTO.CREDIT));
  }

  @Test
  public void givenALoanWheInvokeMapThenReturnLoanDTO() throws Exception {
    final Loan loan = Loan.builder().id("id").name("name").surname("surname").surname2("surname2").birthDate(
        LocalDate.MIN).amount(BigDecimal.TEN)
        .duration(Period.ofYears(5)).amortizedCapital(BigDecimal.ZERO).build();

    final LoanDTO result = this.loanMapper.toLoanDTO(loan);

    assertThat(result, is(notNullValue()));
    assertThat(result.getId(), is(loan.getId()));
    assertThat(result.getName(), is(loan.getName()));
    assertThat(result.getSurname(), is(loan.getSurname()));
    assertThat(result.getSurname2(), is(loan.getSurname2().get()));
    assertThat(result.getBirthDate(), is(loan.getBirthDate()));
    assertThat(result.getAmount(), is(loan.getAmount()));
    assertThat(result.getAmortizedCapital(), is(loan.getAmortizedCapital()));
    assertThat(result.getDuration(), is(loan.getDuration()));
  }

  @Test
  public void givenAMortgageWheInvokeMapThenReturnMortgageDTO() throws Exception {
    final Mortgage mortgage = Mortgage.builder().id("id").name("name").surname("surname").surname2("surname2").birthDate(
        LocalDate.MIN).amount(BigDecimal.TEN)
        .duration(Period.ofYears(5)).amortizedCapital(BigDecimal.ZERO).build();

    final MortgageDTO result = this.mortgageMapper.toMortgageDTO(mortgage);

    assertThat(result, is(notNullValue()));
    assertThat(result.getId(), is(mortgage.getId()));
    assertThat(result.getName(), is(mortgage.getName()));
    assertThat(result.getSurname(), is(mortgage.getSurname()));
    assertThat(result.getSurname2(), is(mortgage.getSurname2().get()));
    assertThat(result.getBirthDate(), is(mortgage.getBirthDate()));
    assertThat(result.getAmount(), is(mortgage.getAmount()));
    assertThat(result.getAmortizedCapital(), is(mortgage.getAmortizedCapital()));
    assertThat(result.getDuration(), is(mortgage.getDuration()));
  }
}
