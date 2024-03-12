package com.example.bootshelf.user.model.entity;

import com.example.bootshelf.board.model.entity.Board;
import com.example.bootshelf.boardcomment.model.entity.BoardComment;
import com.example.bootshelf.boardcommentup.model.entity.BoardCommentUp;
import com.example.bootshelf.boardhistory.model.entity.BoardHistory;
import com.example.bootshelf.boardscrap.model.entity.BoardScrap;
import com.example.bootshelf.boardup.model.entity.BoardUp;
import com.example.bootshelf.review.model.entity.Review;
import com.example.bootshelf.reviewcomment.model.ReviewComment;
import com.example.bootshelf.reviewcommentup.model.ReviewCommentUp;
import com.example.bootshelf.reviewhistory.model.ReviewHistory;
import com.example.bootshelf.reviewscrap.model.ReviewScrap;
import com.example.bootshelf.reviewup.model.ReviewUp;
import com.example.bootshelf.user.model.entity.request.PatchUpdateUserReq;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @OneToMany(mappedBy = "user")
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ReviewUp> reviewUpList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ReviewScrap> reviewScrapList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ReviewHistory> reviewHistoryList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ReviewComment> reviewCommentList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ReviewCommentUp> reviewCommentUpList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<BoardUp> boardUpList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<BoardScrap> boardScrapList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<BoardHistory> boardHistoryList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<BoardComment> boardCommentList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<BoardCommentUp> boardCommentUpList = new ArrayList<>();

    @Column(nullable = false, length = 45, unique = true)
    private String email;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(nullable = false)
    private String authority;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 30)
    private String nickName;

    //    @Column(nullable = false)
    private String profileImage;

    @Column(nullable = false)
    private String createdAt;

    @Column(nullable = false)
    private String updatedAt;

    @Column(nullable = false)
    private Boolean status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton((GrantedAuthority) () -> authority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public String getUsername(){
        return email;
    }
    @Override
    public String getPassword(){
        return password;
    }

    public void update(PatchUpdateUserReq patchUpdateUserReq, String userPassword) {
        if (userPassword != null) {
            this.password = password;
        }
        if (patchUpdateUserReq.getNickName() != null) {
            this.nickName = patchUpdateUserReq.getNickName();
        }
        if (patchUpdateUserReq.getProfileImage() != null) {
            this.profileImage = patchUpdateUserReq.getProfileImage();
        }
    }
}





