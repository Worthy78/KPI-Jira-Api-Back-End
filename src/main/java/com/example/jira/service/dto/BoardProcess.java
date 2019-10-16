package com.example.jira.service.dto;

import com.example.jira.domain.Board;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BoardProcess {
    private List<BoardDto> values;
}
