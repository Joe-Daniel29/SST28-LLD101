package com.example.tickets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * INTENTION: A ticket should be an immutable record-like object.
 *
 * NOW STATE (REFACTORED):
 * - private final fields
 * - private constructor
 * - no setters
 * - unmodifiable tags list
 * - validation centralized in Builder.build()
 * - fluent Builder for object creation
 * - proper equals() and hashCode()
 * - optional booleans represented as Boolean
 */
public final class IncidentTicket {

    private final String id;
    private final String reporterEmail;
    private final String title;

    private final String description;
    private final String priority; // LOW, MEDIUM, HIGH, CRITICAL
    private final List<String> tags;
    private final String assigneeEmail;
    private final Boolean customerVisible; // fixed from boolean
    private final Integer slaMinutes; // optional
    private final String source; // e.g. "CLI", "WEBHOOK", "EMAIL"

    private IncidentTicket(Builder builder) {
        this.id = builder.id;
        this.reporterEmail = builder.reporterEmail;
        this.title = builder.title;
        this.description = builder.description;
        this.priority = builder.priority;
        // Defensive copy to ensure immutability
        this.tags = Collections.unmodifiableList(new ArrayList<>(builder.tags));
        this.assigneeEmail = builder.assigneeEmail;
        this.customerVisible = builder.customerVisible;
        this.slaMinutes = builder.slaMinutes;
        this.source = builder.source;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder()
                .id(this.id)
                .reporterEmail(this.reporterEmail)
                .title(this.title)
                .description(this.description)
                .priority(this.priority)
                .tags(this.tags) // fixed from loop
                .assigneeEmail(this.assigneeEmail)
                .customerVisible(this.customerVisible)
                .slaMinutes(this.slaMinutes)
                .source(this.source);
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getReporterEmail() {
        return reporterEmail;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getAssigneeEmail() {
        return assigneeEmail;
    }

    public Boolean getCustomerVisible() {
        return customerVisible;
    } // fixed from isCustomerVisible

    public Integer getSlaMinutes() {
        return slaMinutes;
    }

    public String getSource() {
        return source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        IncidentTicket that = (IncidentTicket) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(reporterEmail, that.reporterEmail) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(priority, that.priority) &&
                Objects.equals(tags, that.tags) &&
                Objects.equals(assigneeEmail, that.assigneeEmail) &&
                Objects.equals(customerVisible, that.customerVisible) &&
                Objects.equals(slaMinutes, that.slaMinutes) &&
                Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reporterEmail, title, description, priority, tags, assigneeEmail, customerVisible,
                slaMinutes, source);
    }

    @Override
    public String toString() {
        return "IncidentTicket{" +
                "id='" + id + '\'' +
                ", reporterEmail='" + reporterEmail + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", tags=" + tags +
                ", assigneeEmail='" + assigneeEmail + '\'' +
                ", customerVisible=" + customerVisible +
                ", slaMinutes=" + slaMinutes +
                ", source='" + source + '\'' +
                '}';
    }

    public static class Builder {
        private String id;
        private String reporterEmail;
        private String title;

        private String description;
        private String priority;
        private List<String> tags = new ArrayList<>();
        private String assigneeEmail;
        private Boolean customerVisible; // fixed from boolean
        private Integer slaMinutes;
        private String source;

        private Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder reporterEmail(String reporterEmail) {
            this.reporterEmail = reporterEmail;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder priority(String priority) {
            this.priority = priority;
            return this;
        }

        public Builder tags(List<String> tags) { // fixed validation loophole
            this.tags = new ArrayList<>();
            if (tags != null) {
                for (String tag : tags) {
                    if (tag != null && !tag.trim().isEmpty()) {
                        this.tags.add(tag);
                    }
                }
            }
            return this;
        }

        public Builder addTag(String tag) {
            if (this.tags == null) {
                this.tags = new ArrayList<>();
            }
            if (tag != null && !tag.trim().isEmpty()) {
                this.tags.add(tag);
            }
            return this;
        }

        public Builder assigneeEmail(String assigneeEmail) {
            this.assigneeEmail = assigneeEmail;
            return this;
        }

        public Builder customerVisible(Boolean customerVisible) {
            this.customerVisible = customerVisible;
            return this;
        } // fixed from boolean

        public Builder slaMinutes(Integer slaMinutes) {
            this.slaMinutes = slaMinutes;
            return this;
        }

        public Builder source(String source) {
            this.source = source;
            return this;
        }

        public IncidentTicket build() {
            // Centralize validation
            Validation.requireTicketId(this.id);
            Validation.requireEmail(this.reporterEmail, "reporterEmail");
            Validation.requireNonBlank(this.title, "title");
            Validation.requireMaxLen(this.title, 80, "title");

            if (this.assigneeEmail != null && !this.assigneeEmail.trim().isEmpty()) {
                Validation.requireEmail(this.assigneeEmail, "assigneeEmail");
            }

            if (this.priority != null) {
                Validation.requireOneOf(this.priority, "priority", "LOW", "MEDIUM", "HIGH", "CRITICAL");
            }

            if (this.slaMinutes != null) {
                Validation.requireRange(this.slaMinutes, 5, 7200, "slaMinutes");
            }

            if (this.source != null) { // added domain rule consistency
                Validation.requireNonBlank(this.source, "source");
                Validation.requireMaxLen(this.source, 20, "source");
            }

            return new IncidentTicket(this);
        }
    }
}
