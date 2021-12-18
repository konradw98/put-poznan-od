package put.poznan.ochronadanych.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentsDto {
    private Long id;
    private Long postId;
    private String text;
    private String userName;
}
