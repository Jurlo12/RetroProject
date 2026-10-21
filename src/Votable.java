public interface Votable {

    int votesCount();

    void voteBy(User voter);

    // default-метод: готовая реализация, построенная на абстрактном методе выше
    default boolean hasVotes() {
        return votesCount() > 0;
    }
}
