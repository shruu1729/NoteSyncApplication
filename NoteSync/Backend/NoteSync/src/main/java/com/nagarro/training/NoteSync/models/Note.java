package com.nagarro.training.NoteSync.models;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(length = 500)
    private String content;
    private String important;
    

    @ManyToOne
    @JoinColumn(name = "authorname", referencedColumnName = "userName")
    private User author;

    private String createdDate; // Format: dd/mm/yyyy
    private String createdTime; // 24-hour format with seconds (HH:mm:ss)
    private String modifiedDate;
    private String modifiedTime;
    @PrePersist
    public void prePersist() {
        // Automatically set the current date and time when a 'Note' object is created
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date currentDate = new Date();
        this.createdDate = dateFormat.format(currentDate);
        this.createdTime = timeFormat.format(currentDate);
        this.modifiedDate = dateFormat.format(currentDate);
        this.modifiedTime = timeFormat.format(currentDate);
    }

    // Constructors
    public Note() {
        // Default constructor
    }

    public Note(String title, String content, String important, User author) {
        this.title = title;
        this.content = content;
        this.important = important;
        this.author = author;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImportant() {
        return important;
    }

    public void setImportant(String important) {
        this.important = important;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }



    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", important='" + important + '\'' +
                ", author=" + author.getUserName() +
                '}';
    }


	public String getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
}
