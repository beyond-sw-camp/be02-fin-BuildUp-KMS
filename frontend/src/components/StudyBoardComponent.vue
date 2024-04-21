<template>
  <!-- 스터디 게시글 컴포넌트 -->
    <a class="css-1myomkm epbh2v50"  :href="'/board/' + boards.idx">
      <h2 aria-description="글 제목">
        <span
          >{{ boards.title }}
        </span>
      </h2>
      <h3 aria-description="글 내용">
        {{ boards.content }}
      </h3>
      <TagComponent :tagNameList="boards.tagNameList" />
      <div class="css-k57yxr epbh2v51">
        <img
          alt="닉네임입니당 프로필 이미지"
          :src="boards.userProfileImage"
          width="20"
          height="20"
          decoding="async"
          data-nimg="1"
          loading="lazy"
          style="color: transparent"
        />
        <p>
          <span aria-description="유저 닉네임" class="css-1bf50wt epbh2v52"
            >{{ boards.nickName }}</span
          ><span aria-description="글 생성일" class="css-1mmbkao epbh2v53"
            >{{ this.$moment.utc(boards.createdAt).local().format('YYYY-MM-DD HH:mm:ss') }}</span
          >
        </p>
      </div>
      <ul class="css-1k3qs23 epbh2v54">
        <li aria-description="댓글수" class="css-17j46fn epbh2v55">
          <img
            width="10px"
            height="10px"
            src="../assets/img/comment-icon.png"
            alt="chat--v1"
          />{{ boards.commentCnt }}
        </li>
        <li aria-description="좋아요수" class="css-17j46fn epbh2v55">
          <img
            width="14px"
            height="14px"
            src="../assets/img/like-icon.png"
            alt="facebook-like"
          />{{ boards.upCnt }}
        </li>
        <li aria-description="조회수" class="css-17j46fn epbh2v55">
          <img
            width="10px"
            height="10px"
            src="../assets/img/eye-icon.png"
            alt="visible--v1"
            style="color: grey"
          />{{ boards.viewCnt }}
        </li>
      </ul>
    </a>
  <!-- / 스터디 게시글 컴포넌트 -->
</template>

<script>
import TagComponent from "@/components/TagComponent.vue";

export default {
  name: "StudyBoardComponent",
  props: ["boards"],
  components: {
    TagComponent,
  },
};
</script>

<style scoped>
html {
  padding: 0;
  margin: 0;
  line-height: 1.5;
  font-family: Pretendard, serif;
}

body {
  height: 100%;
  margin: 0;
  overflow-x: hidden;
  font-size: 1.4rem;
  box-sizing: border-box;
}

div {
  display: block;
}

* {
  line-height: 1.5;
  box-sizing: border-box;
  letter-spacing: normal;
}

* {
  margin: 0;
  line-height: 1.5;
}

.css-804v49 {
  width: 100%;
  height: 48px;
  background-color: #541d70;
  border-radius: 8px;
  text-align: center;
  font-family: Pretendard;
  font-size: 16px;
  font-weight: 700;
  color: #fff;
  line-height: 48px;
  cursor: pointer;
}

:not(svg) {
  transform-origin: 0 0;
}

.css-1csvk83 {
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 4px;
  background-color: #f6f9fa;
}

@media (min-width: 820px) {
  .css-1csvk83 {
    background-color: #fff;
  }
}

ul {
  list-style: none;
}

@media only screen and (max-width: 61.9375rem) {
  .css-10c0kk0 {
    grid-template-columns: repeat(1, minmax(16.6875rem, 1fr));
  }
}

@media only screen and (max-width: 74.9375rem) {
  .css-10c0kk0 {
    grid-template-columns: repeat(2, minmax(16.6875rem, 1fr));
  }
}

.css-10c0kk0 {
  width: 100%;
  display: grid;
  grid-gap: 1rem;
  grid-template-columns: repeat(3, minmax(15rem, 1fr));
  grid-auto-flow: dense row;
  align-items: center;
  justify-content: space-around;
  position: relative;
  padding-left: 0;
}

.css-1myomkm {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  min-height: 100px;
  background-color: #fff;
  border-radius: 20px;
  gap: 0.5rem;
  padding: 12px;
  background-color: rgb(84, 29, 112, 0.1);
}

a {
  cursor: pointer;
  text-decoration: none;
  font-family: Pretendard;
}

.css-1myomkm h4 {
  font-family: Pretendard;
  font-size: 0.75rem;
  font-weight: 700;
  color: #ff9e2d;
  margin-bottom: 0.25rem;
}

.css-1myomkm h2 {
  overflow: hidden;
  text-overflow: ellipsis;
  justify-content: flex-start;
  color: #000;
  font-family: Pretendard;
  font-size: 14px;
  font-weight: 700;
  line-height: 8px;
  opacity: 1;
  display: -webkit-box;
  text-overflow: ellipsis;
  overflow: hidden;
  min-height: 2.5rem;
  word-break: keep-all;
}

.css-1myomkm h3 {
  color: #777;
  font-family: Pretendard;
  font-weight: 400;
  line-height: 140%;
  opacity: 1;
  display: -webkit-box;
  font-size: 13px;
  text-overflow: ellipsis;
  overflow: hidden;
  word-break: keep-all;
  height: 70px;
}

.css-k57yxr {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  gap: 0.5rem;
  margin: 0.5rem 0;
}

.css-k57yxr img {
  width: 22px;
  height: 22px;
  border-radius: 50%;
  background-color: #fff;
  overflow: hidden;
  object-fit: contain;
  flex-shrink: 0;
}

.css-k57yxr > p {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: flex-start;
  gap: 0.125rem;
  width: calc(100% - 2.25rem);
}

.css-1bf50wt {
  display: -webkit-box;
  text-overflow: ellipsis;
  overflow: hidden;
  color: #000;
  font-family: Pretendard;
  font-size: 0.89rem;
  font-weight: 400;
  line-height: 120%;
  font-size: 11px;
  opacity: 1;
  width: 100%;
}

.css-1mmbkao {
  color: #777;
  font-family: Pretendard;
  font-size: 10px;
  font-weight: 400;
  line-height: 120%;
  opacity: 1;
}

.css-k57yxr > p {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: flex-start;
  gap: 0.125rem;
  width: calc(100% - 2.25rem);
}

.css-k57yxr {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  gap: 0.5rem;
  margin: 0.5rem 0;
}

.css-1k3qs23 {
  display: flex;
  justify-content: flex-start;
  gap: 0.5rem;
  padding-left: 0;
  align-items: center;
  justify-content: baseline;
}

.css-17j46fn {
  display: flex;
  justify-content: flex-start;
  color: #a3a3a3;
  font-family: Pretendard;
  font-size: 10px;
  font-weight: 400;
  line-height: 120%;
  opacity: 1;
  align-items: center;
  gap: 0.2rem;
}

.css-li-001 {
  letter-spacing: -0.009em;
  font-family: Pretendard;
  line-height: 1.6;
  list-style: none;
}

.css-li-001:hover {
  border: 2px solid rgb(84, 29, 112, 0.3);
  border-radius: 20px;
}

</style>
