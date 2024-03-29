package gatech.edu.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
public class ProjectTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String taskSequence;
    @NotBlank(message = "Please give a brief summary.")
    private String summary;
    private String acceptanceCriteria;
    private Integer priority;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm a z")
    private Date createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm a z")
    private Date updatedAt;

    private String projectId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "backlog", nullable = false)
    @JsonIgnore
    private Backlog backlog;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

    public ProjectTask() {
    }

    public long getId() {
        return id;
    }

    public String getTaskSequence() {
        return taskSequence;
    }

    public String getSummary() {
        return summary;
    }

    public String getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    public Integer getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getProjectId() {
        return projectId;
    }

    public Backlog getBacklog() {
        return backlog;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTaskSequence(String taskSequence) {
        this.taskSequence = taskSequence;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setAcceptanceCriteria(String acceptanceCriteria) {
        this.acceptanceCriteria = acceptanceCriteria;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setBacklog(Backlog backlog) {
        this.backlog = backlog;
    }

    @Override
    public String toString() {
        return "ProjectTask{" +
                "id=" + id +
                ", taskSequence='" + taskSequence + '\'' +
                ", summary='" + summary + '\'' +
                ", acceptanceCriteria='" + '\'' +
                ", priority=" + priority +
                ", status='" + status + '\'' +
                ", dueDate=" + dueDate +
                ", projectId=" + projectId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
