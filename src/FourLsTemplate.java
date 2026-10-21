import java.util.List;

public class FourLsTemplate implements RetroTemplate {
        @Override
        public String title() {
            return "Liked / Learned / Lacked / Longed for.";
        }

        @Override
        public List<String> columns() {
            return List.of("Liked", "Learned", "Lacked", "Longed for");
        }
}
