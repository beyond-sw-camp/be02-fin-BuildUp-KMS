<template>
    <!-- 스터디 게시글 컴포넌트 -->
            <li class="css-li-001">
                <a class="css-1myomkm epbh2v50" href="#">
                    <h2 aria-description="글 제목">
                        <span>신대방삼거리역 근처 스터디 모집합니다. 백엔드
                            개발자 필요합니다.
                        </span>
                    </h2>
                    <h3 aria-description="글 내용">
                        프론트 개발자 2명 모집합니다. 현재 백엔드 개발자 2명
                        있습니다. 함께 아이템 선정부터 개발, 기획 모두
                        함께해요 많이 연락주세요!
                    </h3>
                    <div class="css-k57yxr epbh2v51">
                        <img alt="닉네임입니당 프로필 이미지"
                            src="https://d2u3dcdbebyaiu.cloudfront.net/uploads/atch_img/560/8f741dd9ca7ceff5d6c856ba4ae861e7_res.jpeg"
                            width="20" height="20" decoding="async" data-nimg="1" loading="lazy"
                            style="color: transparent" />
                        <p>
                            <span aria-description="유저 닉네임" class="css-1bf50wt epbh2v52">닉네임입니당</span><span
                                aria-description="글 생성일" class="css-1mmbkao epbh2v53">2024-03-07</span>
                        </p>
                    </div>
                    <ul class="css-1k3qs23 epbh2v54">
                        <li aria-description="조회수" class="css-17j46fn epbh2v55">
                            <img width="10px" height="10px" src="../assets/img/eye-icon.png" alt="visible--v1"
                                style="color: grey" />1
                        </li>
                        <li aria-description="댓글수" class="css-17j46fn epbh2v55">
                            <img width="10px" height="10px" src="../assets/img/eye-icon.png" alt="chat--v1" />1
                        </li>
                        <li aria-description="좋아요수" class="css-17j46fn epbh2v55">
                            <img width="14px" height="14px" src="../assets/img/eye-icon.png" alt="facebook-like" />1
                        </li>
                    </ul>
                </a>
            </li>

    <!-- / 스터디 게시글 컴포넌트 -->
</template>

<script></script>

<style scoped>
html {
    padding: 0;
    margin: 0;
    line-height: 1.5;
    font-family: Pretendard, serif
}

body {
    height: 100%;
    margin: 0;
    overflow-x: hidden;
    font-size: 1.4rem;
    box-sizing: border-box
}

div {
    display: block
}

* {
    line-height: 1.5;
    box-sizing: border-box;
    letter-spacing: normal
}

* {
    margin: 0;
    line-height: 1.5
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
    cursor: pointer
}

:not(svg) {
    transform-origin: 0 0
}

.css-1csvk83 {
    width: 100%;
    display: flex;
    flex-direction: column;
    gap: 4px;
    background-color: #f6f9fa
}

@media (min-width:820px) {
    .css-1csvk83 {
        background-color: #fff
    }
}

ul {
    list-style: none
}

@media only screen and (max-width:61.9375rem) {
    .css-10c0kk0 {
        grid-template-columns: repeat(1, minmax(16.6875rem, 1fr))
    }
}

@media only screen and (max-width:74.9375rem) {
    .css-10c0kk0 {
        grid-template-columns: repeat(2, minmax(16.6875rem, 1fr))
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
    padding-left: 0
}

.css-1myomkm {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    min-height: 100px;
    background-color: #fff;
    border-radius: 20px;
    gap: .5rem;
    padding: 12px;
    background-color: rgb(84, 29, 112, .1)
}

a {
    cursor: pointer;
    text-decoration: none;
    font-family: Pretendard
}

.css-1myomkm h4 {
    font-family: Pretendard;
    font-size: .75rem;
    font-weight: 700;
    color: #ff9e2d;
    margin-bottom: .25rem
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
    word-break: keep-all
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
    height: 70px
}

.css-k57yxr {
    display: flex;
    justify-content: flex-start;
    align-items: center;
    gap: .5rem;
    margin: .5rem 0
}

.css-k57yxr img {
    width: 22px;
    height: 22px;
    border-radius: 50%;
    background-color: #fff;
    overflow: hidden;
    object-fit: contain;
    flex-shrink: 0
}

.css-k57yxr>p {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    justify-content: flex-start;
    gap: .125rem;
    width: calc(100% - 2.25rem)
}

.css-1bf50wt {
    display: -webkit-box;
    text-overflow: ellipsis;
    overflow: hidden;
    color: #000;
    font-family: Pretendard;
    font-size: .89rem;
    font-weight: 400;
    line-height: 120%;
    font-size: 11px;
    opacity: 1;
    width: 100%
}

.css-1mmbkao {
    color: #777;
    font-family: Pretendard;
    font-size: 10px;
    font-weight: 400;
    line-height: 120%;
    opacity: 1
}

.css-k57yxr>p {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    justify-content: flex-start;
    gap: .125rem;
    width: calc(100% - 2.25rem)
}

.css-k57yxr {
    display: flex;
    justify-content: flex-start;
    align-items: center;
    gap: .5rem;
    margin: .5rem 0
}

.css-1k3qs23 {
    display: flex;
    justify-content: flex-start;
    gap: .5rem;
    padding-left: 0;
    align-items: center;
    justify-content: baseline
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
    gap: .2rem
}

.css-li-001 {
    letter-spacing: -.009em;
    font-family: Pretendard;
    line-height: 1.6;
    list-style: none
}

.css-li-001:hover {
    border: 2px solid rgb(84, 29, 112, .3);
    border-radius: 20px
}
</style>