package com.apis.apisjwtswagger.Controller;

import com.apis.apisjwtswagger.DTO.Comments.CreateCommentDTO;
import com.apis.apisjwtswagger.DTO.Comments.ShowCommentDTO;
import com.apis.apisjwtswagger.Entity.CommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="Spring")
public interface CommentsMapStruct {
    @Mapping(target="author" , source="author.email")
     ShowCommentDTO toShowCommentsDTO(CommentEntity commentEntity
    );

    CommentEntity toCommentEntity(CreateCommentDTO commentDTO);
}
