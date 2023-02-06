package technoDom;

public enum Locale {
    KZ("Қаз"),
    RU("Рус");
    private final String desc;

    Locale(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
