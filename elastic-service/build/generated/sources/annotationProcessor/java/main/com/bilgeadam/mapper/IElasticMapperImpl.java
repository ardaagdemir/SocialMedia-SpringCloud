package com.bilgeadam.mapper;

import com.bilgeadam.rabbitmq.model.RegisterElasticModel;
import com.bilgeadam.repository.entity.UserProfile;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-10T17:24:20+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class IElasticMapperImpl implements IElasticMapper {

    @Override
    public UserProfile fromElasticToUserProfile(RegisterElasticModel registerElasticModel) {
        if ( registerElasticModel == null ) {
            return null;
        }

        UserProfile.UserProfileBuilder<?, ?> userProfile = UserProfile.builder();

        userProfile.id( registerElasticModel.getId() );
        userProfile.authId( registerElasticModel.getAuthId() );
        userProfile.username( registerElasticModel.getUsername() );
        userProfile.email( registerElasticModel.getEmail() );

        return userProfile.build();
    }
}
