import java.util.Set;

public class StateCard {
    private String description;
    private String state;
    private String comments;

    private static final Set<String> ALLOWED_STATES = Set.of("new", "in progress", "completed", "archived");

    public StateCard(String description, String state, String comments) {
        setDescription(description);
        setState(state);
        setComments(comments);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description must not be null or empty.");
        }
        this.description = description.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        if (state == null || state.trim().isEmpty()) {
            throw new IllegalArgumentException("State must not be null or empty.");
        }

        String normalizedState = state.trim().toLowerCase();
        if (!ALLOWED_STATES.contains(normalizedState)) {
            throw new IllegalArgumentException("Invalid state. Allowed values: new, in progress, completed, archived.");
        }

        this.state = normalizedState;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = (comments == null) ? null : comments.trim();
    }
}
