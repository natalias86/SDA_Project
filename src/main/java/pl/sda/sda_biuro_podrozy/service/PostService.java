package pl.sda.sda_biuro_podrozy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.sda_biuro_podrozy.dto.PostDto;
import pl.sda.sda_biuro_podrozy.entities.HotelEntity;
import pl.sda.sda_biuro_podrozy.entities.PostEntity;
import pl.sda.sda_biuro_podrozy.repository.PostRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;

    }

    public void addPost(PostDto postDto) {
        PostEntity post = new PostEntity();
        post.setPostTitle(postDto.getPostTitle());
        post.setPostDescription(postDto.getPostDescription());
        post.setPrice(postDto.getPrice());
        post.setStartDate(LocalDate.parse(postDto.getStartDate()));
        post.setEndDate(LocalDate.parse(postDto.getEndDate()));
        post.setVacancy(postDto.getVacancy());
        post.setType(postDto.getType());
        post.setHotelEntity(postDto.getHotelEntity());
        post.setPromoted(postDto.isPromoted());
        postRepository.save(post);
    }

    public void editSinglePost(PostDto postDto, Integer postId) {
        PostEntity postEdited = postRepository.findById(postId).get();
        postEdited.setPostTitle(postDto.getPostTitle());
        postEdited.setPostDescription(postDto.getPostDescription());
        postEdited.setPrice(postDto.getPrice());
        postEdited.setStartDate(LocalDate.parse(postDto.getStartDate()));
        postEdited.setEndDate(LocalDate.parse(postDto.getEndDate()));
        postEdited.setVacancy(postDto.getVacancy());
        postEdited.setType(postDto.getType());
        postEdited.setHotelEntity(postDto.getHotelEntity());
        postEdited.setPromoted(postDto.isPromoted());
        postRepository.save(postEdited);
    }

    public List<PostEntity> getPublishedPosts() {
        List<PostEntity> posts = postRepository.findAll();
        return posts;
    }

    public PostDto getSinglePost(Integer postId) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(RuntimeException::new);
        PostDto singlePost = new PostDto();
        singlePost.setPostId(postId);
        singlePost.setPostTitle(postEntity.getPostTitle());
        singlePost.setPostDescription(postEntity.getPostDescription());
        singlePost.setPrice(postEntity.getPrice());
        singlePost.setStartDate(String.valueOf(postEntity.getStartDate()));
        singlePost.setEndDate(String.valueOf(postEntity.getEndDate()));
        singlePost.setType(postEntity.getType());
        singlePost.setVacancy(postEntity.getVacancy());
        singlePost.setHotelEntity(postEntity.getHotelEntity());
        return singlePost;
    }


/*    public List<PostEntity> getPostsByHotelStandard(Integer hotelStandard) {
        List<PostEntity> postsByHotelStandard = postRepository.findByHotelEntity_Standard(hotelStandard);
        return postsByHotelStandard;
    }*/
/*    public List<PostEntity> getPostsByHotel(Integer hotelId) {
        List<PostEntity> postsByHotelStandard = postRepository.finAllBy
        return postsByHotelStandard;
    }*/


}

