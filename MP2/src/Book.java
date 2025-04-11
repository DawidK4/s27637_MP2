import java.time.LocalDate;

public class Book {
    private String ibsn;
    private String title;
    private String genre;
    private String publishingHouseName;
    private LocalDate releaseDate;

    public Book(String ibsn, String title, String genre, String publishingHouseName, LocalDate releaseDate) {
        setIbsn(ibsn);
        setTitle(title);
        setGenre(genre);
        setPublishingHouseName(publishingHouseName);
        setReleaseDate(releaseDate);
    }

    public String getIbsn() {
        return ibsn;
    }

    public void setIbsn(String ibsn) {
        if (ibsn == null || ibsn.trim().isEmpty()) {
            throw new IllegalArgumentException("IBSN must not be null or empty.");
        }
        this.ibsn = ibsn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title must not be null or empty.");
        }
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        if (genre == null || genre.trim().isEmpty()) {
            throw new IllegalArgumentException("Genre must not be null or empty.");
        }
        this.genre = genre;
    }

    public String getPublishingHouseName() {
        return publishingHouseName;
    }

    public void setPublishingHouseName(String publishingHouseName) {
        if (publishingHouseName == null || publishingHouseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Publishing house name must not be null or empty.");
        }
        this.publishingHouseName = publishingHouseName;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        if (releaseDate == null) {
            throw new IllegalArgumentException("Release date must not be null.");
        }
        if (releaseDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Release date cannot be in the future.");
        }
        this.releaseDate = releaseDate;
    }
}
