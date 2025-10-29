package com.apis.apisjwtswagger.Controller;
import com.apis.apisjwtswagger.DTO.Posts.PostContentUpdateDTO;
import com.apis.apisjwtswagger.DTO.Posts.PostTitleUpdateDTO;
import com.apis.apisjwtswagger.DTO.Posts.PostsCreateDTO;
import com.apis.apisjwtswagger.DTO.Posts.PostsReadDTO;
import com.apis.apisjwtswagger.Entity.PostsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostsMapStruct {
    @Mapping(target = "user", source = "author_post.id")
    PostsReadDTO toPostsDTO(PostsEntity post);

    PostsEntity toPostsEntity(PostsReadDTO post);

    PostsEntity toPostsEntity(PostsCreateDTO dto);

    PostsEntity toPostsEntity(PostTitleUpdateDTO dto);

    PostsEntity toPostsEntity(PostContentUpdateDTO dto);

}
