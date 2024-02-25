package io.github.realyusufismail.slash;

import org.junit.jupiter.api.BeforeEach;

public interface ITestSlashCommand {

    @BeforeEach
    void setUp();

    void shouldReturnCorrectName();

    void shouldReturnCorrectDescription();

    void shouldSendResponse();
}
