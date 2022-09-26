package co.com.sofkoin.gamma.application.commons.views;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ActivityView extends View {
    private String activityId;
    private LocalDateTime timestamp;
    private String type;
}
