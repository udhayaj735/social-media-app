package com.socialmedia.socialmediaapp.payload;

import com.socialmedia.socialmediaapp.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse {
    /*created for pagination post response*/

    /*-->List of Page DTO
    -->Page Number
    -->Page size
    -->total elements
    -->total pages
    -->is this last page*/

    private List<PostDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean isLastPage;
}
