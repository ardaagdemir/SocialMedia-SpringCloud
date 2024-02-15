package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.CreateNewPostRequestDto;
import com.bilgeadam.dto.response.UserProfileResponseDto;
import com.bilgeadam.repository.entity.Like;
import com.bilgeadam.repository.entity.Post;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-10T17:24:28+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class IPostMapperImpl implements IPostMapper {

    @Override
    public Post toPost(CreateNewPostRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Post.PostBuilder<?, ?> post = Post.builder();

        post.content( dto.getContent() );
        List<String> list = dto.getMediaUrls();
        if ( list != null ) {
            post.mediaUrls( new ArrayList<String>( list ) );
        }

        return post.build();
    }

    @Override
    public Like toLike(UserProfileResponseDto userProfile) {
        if ( userProfile == null ) {
            return null;
        }

        Like.LikeBuilder<?, ?> like = Like.builder();

        like.userId( userProfile.getUserId() );
        like.username( userProfile.getUsername() );
        like.avatar( userProfile.getAvatar() );

        return like.build();
    }
}
