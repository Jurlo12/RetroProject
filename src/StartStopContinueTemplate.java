import java.util.List;

public class StartStopContinueTemplate implements RetroTemplate {

    @Override
    public String title() {
        return "Start / Stop / Continue";
    }

    @Override
    public List<String> columns() {
        return List.of("Start", "Stop", "Continue");
    }
}
