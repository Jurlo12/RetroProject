import java.util.List;

public class DiscussionTopicsTemplate implements RetroTemplate {

    @Override
    public String title() {
        return "Темы для обсуждения";
    }

    @Override
    public List<String> columns() {
        return List.of("Темы для обсуждения", "Что прошло хорошо");
    }
}
