package dev.ashish.qa.support;

public final class Configuration {
    private Configuration() {
    }

    public static String baseUrl() {
        return value("BASE_URL", "https://www.saucedemo.com");
    }

    public static String username() {
        return value("TEST_USERNAME", "standard_user");
    }

    public static String password() {
        return value("TEST_PASSWORD", "secret_sauce");
    }

    public static String browser() {
        return value("BROWSER", "chrome").toLowerCase();
    }

    private static String value(String name, String fallback) {
        String configured = System.getenv(name);
        return configured == null || configured.isBlank() ? fallback : configured;
    }
}
