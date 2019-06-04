package com.xabe.autoclose;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AutoCloseTest {

    @Test
    public void givenAClassNotAutoCloseInterfaceWhenCloseThenVerifyInvokeMethodClose() throws Exception {
        //Given
        final NotAutoclosable notAutoclosable = new NotAutoclosable();

        //When
        try (final var autoClosableSupplier = AutoCloser.useResource(notAutoclosable).closeWith(sp -> sp.dispose())) {
            assertThat(notAutoclosable.isOpened(), is(true));
        }

        //Then
        assertThat(notAutoclosable.isOpened(), is(false));
    }
}
