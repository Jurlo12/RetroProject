public abstract class User extends BaseEntity {

    private final String name;
    private final String email;

    protected User(String name, String email) {
        super();                         // неявно было бы то же самое
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Некорректный email: " + email);
        }
        this.name = name.trim();
        this.email = email.toLowerCase();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // каждый потомок обязан ответить сам: это и есть полиморфизм
    public abstract boolean canManageRetro();
    public abstract boolean canVote();

    protected abstract String role();

    // метод родителя вызывает role() потомка (идея Template Method)
    public String describe() {
        return name + " (" + role() + ")";
    }
}
