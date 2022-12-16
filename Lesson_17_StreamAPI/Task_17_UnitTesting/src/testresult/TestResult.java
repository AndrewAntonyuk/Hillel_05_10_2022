package testresult;

import java.time.LocalDateTime;

public class TestResult {
    private int summary;
    private int passed;
    private int failed;
    private LocalDateTime startTime;
    private String name;

    public TestResult(int summary, int passed, int failed, LocalDateTime startTime, String name) {
        this.summary = summary;
        this.passed = passed;
        this.failed = failed;
        this.startTime = startTime;
        this.name = name;
    }

    @Override
    public String toString() {
        return "{Name: " + name +
                "; summary: " + summary +
                "; passed: " + passed +
                "; failed: " + failed + "}";
    }

    public int getSummary() {
        return summary;
    }

    public void setSummary(int summary) {
        this.summary = summary;
    }

    public int getPassed() {
        return passed;
    }

    public void setPassed(int passed) {
        this.passed = passed;
    }

    public int getFailed() {
        return failed;
    }

    public void setFailed(int failed) {
        this.failed = failed;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
