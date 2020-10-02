package RestPractice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsPojo {

private String author;
private String title;

    public NewsPojo(String author, String title) {
        this.author = author;
        this.title = title;
    }
public NewsPojo(){}
    @Override
    public String toString() {
        return "NewsPojo{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
