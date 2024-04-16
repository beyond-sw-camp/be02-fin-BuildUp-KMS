## 🔥 STACKS

<br>
<div align=center>
&nbsp;&nbsp;&nbsp;<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat&logo=springBoot&logoColor=white&color=green"/></a></a>
&nbsp;&nbsp;&nbsp;<img src="https://img.shields.io/badge/Spring Security-6DB33F?style=flat&logo=springsecurity&logoColor=white&color=darkgreen"/></a></a>
&nbsp;&nbsp;&nbsp;<img src="https://img.shields.io/badge/Spring JPA-6DB33F?style=flat&logo=spring JPA&logoColor=white&color=green"/></a></a>
&nbsp;&nbsp;&nbsp;<img src="https://img.shields.io/badge/QueryDSL-%231785D1.svg?&style=flat&logo=QueryDSL&logoColor=white" alt="QueryDSL"/></a>
&nbsp;&nbsp;&nbsp;<img src="https://img.shields.io/badge/Amazon AWS-232F3E?style=flat&logo=AmazonAWS&logoColor=black&color=orange"/></a></a>
&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://img.shields.io/badge/Amazon S3-569A31?style=flat&logo=Amazon S3&logoColor=white&color=red"/></a></a>
<br>
</div>

<br>

### 📌 API 테스트 결과

<details>
<summary><b>🫅🏻 회&nbsp;&nbsp;&nbsp;&nbsp;원</b></summary><br>
    <div>
         <details>
         <summary><b>KMS_001_USER_001. 회원 가입</b></summary>
                  <br>
         <p><b>➡️ 회원이 회원 정보 [ 이메일, 패스워드, 이름, 닉네임 ] 를 입력하여 회원 가입을 진행한다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/76ebda5a-aec9-4cfc-a1c7-aab563364a4e"/></p>
         </details><br>
          <details>
         <summary><b>KMS_003_USER-003. 과정명 확인</b></summary>
                  <br>
         <p><b> ➡️ 회원이 캡처한 사진의 과정명이 DB에 있는지 확인하여 인증 회원가입을 진행한다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/8278a355-2c59-445b-8ac4-6ab2ca568792"/></p>
         </details><br>
          <details>
         <summary><b>KMS_004_USER_004. 이메일 인증</b></summary>
                  <br>
         <p><b>➡️ 회원이 메일함으로 온 이메일을 클릭하여 이메일 인증을 진행한다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/e08b7dca-4261-4174-a75f-963e6e61cd15"/></p>
         </details><br>
	 <details>
         <summary><b>KMS_005_USER_005. 일반 로그인</b></summary>
                  <br>
         <p><b>➡️ 회원이 [ 이메일, 패스워드 ] 를 입력하여 로그인을 시도한다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/8b7342b5-fd5b-4aed-8a18-6204e412a8bf"/></p>
         </details><br>
         <details>
         <summary><b>KMS_007_USER_007. 회원정보 상세 조회</b></summary>
                  <br>
         <p><b> ➡️ 회원이 본인의 회원 정보를 조회할 수 있다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/990532d5-27c5-4b78-922b-6fd9ba2f2158"/></p>
         </details><br>
	 <details>
         <summary><b>KMS_008_USER_008. 회원정보 수정</b></summary>
                  <br>
         <p><b> ➡️ 회원이 회원 정보와 관련된 정보 [ 패스워드, 닉네임, 프로필 이미지 ] 를 수정할 수 있다.<br>
                ➡️ 패스워드는 기존 패스워드와 다른 패스워드로만 변경할 수 있다.<br>
                ➡️ 닉네임은 이미 사용중인 닉네임으로 변경할 수 없다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/e5363cf7-1bca-4ad6-b490-377c6dafc2f6"/></p>
         </details><br>
          <details>
         <summary><b>KMS_009_USER_009. 회원정보 수정 시 비밀번호 확인</b></summary>
                  <br>
         <p><b> ➡️ 회원이 회원 정보를 수정하기 위해서 비밀번호를 입력한다.
         <br>
                ➡️ 입력한 비밀번호와 DB에 저장된 비밀번호가 다르면 회원 정보를 수정할 수 없다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/abe0f334-a192-4edf-ae97-ec67c1d7a5ac"/></p>
         </details><br>
           <details>
         <summary><b>KMS_010_USER_010. 회원 프로필 이미지 수정</b></summary>
                  <br>
         <p><b>  ➡️ 회원이 본인의 프로필 이미지를 수정할 수 있다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/678721f0-d11a-4b56-9584-3bc0989b3003"/></p>
         </details><br>
	 <details>
         <summary><b>KMS_011_USER_011. 회원 탈퇴</b></summary>
                  <br>
         <p><b> ➡️ 회원이 회원 탈퇴를 할 수 있다.<br>
                ➡️ 회원 탈퇴를 하면 DB의 회원 테이블 status가 true -> false 로 변경된다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/ac3e7b83-feee-4ab4-8f85-ae7bec007bc8"/></p>
         </details><br>
         	 <details>
         <summary><b>KMS_012_USER_012. 이메일 찾기</b></summary>
                  <br>
         <p><b> ➡️ 회원이 자신의 이메일을 찾을 수 있다.<br>
                ➡️ 회원의 닉네임과 이름으로 이메일을 조회한다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/09d0b6f1-6343-460e-b7af-cccd6dd1dbd8"/></p>
         </details><br>
         	 <details>
         <summary><b>KMS_013_USER_013. 비밀번호 찾기</b></summary>
                  <br>
         <p><b> ➡️ 회원이 자신의 비밀번호를 찾을 수 있다.<br>
                ➡️ 회원이 비밀번호 찾기시 임시 비밀번호를 발급하여 이메일로 전송한다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/cff8f230-7fc0-4b98-ab3e-b160389f8b66"/></p>
         </details><br>
</details>
<br>
<details>
<summary><b>🔍 검&nbsp;&nbsp;&nbsp;&nbsp;색</b></summary><br>
    <div>
<details>
         <summary><b>KMS_014_SEARCH_001. 카테고리 별 [게시글] 검색</b></summary>
                  <br>
         <p><b>➡️ 회원이 검색창에 원하는 단어를 입력하여 게시글 목록을 조회할 수 있다.<br>
               ➡️ 회원이 지식, QnA, 스터디 게시판 / 후기 게시판을 통틀어 검색한 결과를 확인할 수 있다. 
         </b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/e0ef7068-436c-4dd3-ad63-e0aadd2a3a99"/></p>
         </details><br>
         <details>
         <summary><b>KMS_015_SEARCH_002. 카테고리 별 [후기] 검색</b></summary>
                  <br>
         <p><b>➡️ 회원이 검색어를 입력하여 카테고리 별로 후기를 조회할 수  있다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/76becd96-0726-4d76-8eda-0aaefe3bda13"/></p>
         </details><br>
         <details>
         <summary><b>KMS_016_SEARCH_003. 메인페이지 [후기] 검색</b></summary>
                  <br>
         <p><b>➡️ 회원이 메인페이지에서 검색어를 입력하여 후기글을 조회할 수 있다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/204a12d9-4005-407e-8c60-492af33d0c88"/></p>
         </details><br>
             <details>
         <summary><b>KMS_017_SEARCH_004. 검색 결과 페이지에서 [후기]검색</b></summary>
                  <br>
         <p><b>➡️ 메인페이지에서 검색 후 페이지에서 후기글을 조회할 수 있다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/7d582597-fdc7-484e-bc8c-c497fa991787"/></p>
         </details><br>
</details>
<br>
<details>
<summary><b>📋 게&nbsp;&nbsp;시&nbsp;&nbsp;글</b></summary><br>
    <div>
	 <details>
         <summary><b>KMS_018_BOARD_001. 게시글 작성</b></summary>
                  <br>
         <p><b>➡️ 회원이 공유하고자 하는 게시글을 작성할 수 있다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/5810155e-5d84-40c3-ab75-1eb01b02e100"/></p>
         </details><br>
         <details>
         <summary><b>KMS_019_BOARD_002. 게시글 이미지 업로드</b></summary>
                  <br>
         <p><b> ➡️ 회원이 게시글을 작성할 때, 이미지를 첨부할 수 있다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/72b3948d-1596-40bf-8bc4-10a8a9d2e8f1"/></p>
         </details><br>
	 <details>
         <summary><b>KMS_020_BOARD_003. 본인 게시글 목록 조회</b></summary>
                  <br>
         <p><b> ➡️ 회원이 자신이 작성한 게시글 전체를 조회할 수 있다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/858bca64-c77e-4aec-b437-e7eb6c6fa538"/></p>
         </details><br>
	 <details>
         <summary><b>KMS_021_BOARD_004 본인 게시글 상세 조회</b></summary>
                  <br>
         <p><b>➡️ 모든 사용자가 자신이 작성한 게시글을 상세조회할 수 있다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/a87b7db5-f7d5-4e92-9c76-7217c4847e2c"/></p>
         </details><br>
	 <details>
         <summary><b>KMS_022_BOARD_005. 본인 게시글 목록 카테고리 별 조회</b></summary>
                  <br>
         <p><b>➡️ 회원 ( 인증회원, 일반회원 ) 이 자신이 작성한 게시글의 목록을 "마이페이지" 에서 카테고리 별로 조회할 수 있다.<br>  
              ➡️ 카테고리 목록 : 지식, QnA, 스터디 모집</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/8b257b48-b7f6-4000-9c74-f9395d18fade"/></p>
         </details><br>
          <details>
         <summary><b>KMS_023_BOARD_006. 게시글 상세 조회</b></summary>
                  <br>
          <p><b> ➡️ 모든 사용자가 게시글의 상세 내용을 조회할 수 있다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/6b25d077-91e9-4c8f-ab3f-4d6f2dd729d6"/></p>
         </details><br>
                <details>
         <summary><b>KMS_024_BOARD_007. 게시판 카테고리 별 조회</b></summary>
                  <br>
          <p><b>➡️ 모든 사용자가 게시판 카테고리별 게시글을 조회할 수 있다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/3aff27f6-fcff-47df-b0ef-869faa371c3d"/></p>
         </details><br>
                     <details>
         <summary><b>KMS_025_BOARD_008. 태그 별 조회</b></summary>
                  <br>
          <p><b> ➡️ 모든 사용자가 인기 태그 별 게시글을 정렬 조건 별로 조회할 수 있다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/2068b76b-b523-42c4-81dd-f32c7fb82cb6"/></p>
         </details><br>
         	 <details>
         <summary><b>KMS_026_BOARD_009. 태그 별 검색어 조회</b></summary>
                  <br>
         <p><b> ➡️ 모든 사용자가 검색어를 입력하여 검색어 별 / 태그 별 / 정렬조건 별로 게시글을 조회할 수 있다. </b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/b7cf090f-ce73-4597-91cd-0bcdc50e63a0"/></p>
         </details><br>
          <details>
         <summary><b>KMS_028_BOARD_011. 인기 게시글 카테고리 별 조회</b></summary>
                  <br>
         <p><b> ➡️ 모든 사용자가 검색어를 입력하여 검색어 별 / 카테고리 별 / 정렬조건 별로 인기 게시글을 조회할 수 있다. <br>
           ➡️ 카테고리 목록 : 지식, QnA, 스터디 모집</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/714bd066-fc8f-4a29-8e6f-e3561bc50469"/></p>
         </details><br>
         	 <details>
         <summary><b>KMS_029_BOARD_012. 인기 게시글 검색어로 조회</b></summary>
                  <br>
         <p><b> ➡️ 모든 사용자가 검색어를 입력하여 인기 게시글을 정렬조건 별로 조회할 수 있다.<br>
          ➡️ 정렬 조건 : 최신순, 추천순, 조회순, 스크랩순, 댓글순 </b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/a237d460-7c03-4d18-8acb-916ff071e712"/></p>
         </details><br>
	 <details>
         <summary><b>KMS_030_BOARD_013. 게시글 수정</b></summary>
                  <br>
         <p><b> ➡️ 회원 ( 인증회원, 일반회원 ) 이 등록한 자신의 게시글을 수정할 수 있다.<br>
                ➡️ 수정 가능 항목 : 카테고리, 태그, 제목, 본문</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/99ebd39d-addd-4c18-9bdf-7c4e8ffd83a0"/></p>
         </details><br>
	 <details>
         <summary><b>KMS_031_BOARD_014. 게시글 삭제</b></summary>
                  <br>
         <p><b> ➡️ 회원 ( 인증회원, 일반회원 ) 이 등록한 자신의 게시글을 삭제할 수 있다.<br>
                ➡️ 삭제한 게시글은 DB에서 status가 true -> false 로 변경된다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/bc5089e0-1f2c-4234-9228-dd69634a1b5a"/></p>
         </details><br>
</details>
<br>
<details>
<summary><b>✏️ 후&nbsp;&nbsp;&nbsp;&nbsp;기</b></summary><br>
	 <details>
         <summary><b>KMS_032_REVIEW_001. 후기 작성</b></summary>
                  <br>
         <p><b> ➡️ 인증회원이 후기 카테고리 2개 ( 과정 후기, 강사 후기 ) 중 1개를 선택할 수 있다.<br>
                ➡️ 인증회원이 수강중인 또는 수강했던 과정명을 입력할 수 있다.<br>
                ➡️ 선택한 카테고리에 대한 후기를 [ 제목, 평점, 내용 ] 을 입력하여 작성할 수 있다.<br>
              </b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/2a6d9e3b-b636-4525-8119-4401b8d10d40"/></p>
         </details><br>
         <details>
         <summary><b>KMS_033_REVIEW_002. 후기 이미지 업로드</b></summary>
                  <br>
         <p><b>  ➡️ 회원이 후기글을 작성할 때, 이미지를 첨부할 수 있다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/f56c8315-1dd3-4911-8710-58fd076a8c6b"/></p>
         </details><br>
          <details>
         <summary><b>KMS_034_REVIEW_003. 본인 후기글 카테고리 별 목록 조회</b></summary>
                  <br>
         <p><b> ➡️ 인증회원이 자신이 작성한 후기글의 목록을 "마이페이지" 에서 카테고리 별로 조회할 수 있다.<br>
                ➡️ 카테고리 목록 : 과정 후기, 강사 후기<br>
                 ➡️ 정렬 조건 : 최신순, 추천순, 조회순, 스크랩순, 댓글순</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/adda2859-6528-4ea0-bcb4-84ff692d7242"/></p>
         </details><br>
          <details>
         <summary><b>KMS_035_REVIEW_004. 후기글 목록 조회</b></summary>
                  <br>
         <p><b>  ➡️ 모든 사용자가 후기글을 카테고리별 / 정렬조건별로 조회할 수 있다. <br>
                ➡️ 카테고리 목록 : 과정 후기, 강사 후기<br>
                ➡️ 정렬 조건 : 최신순, 추천순, 조회순, 스크랩순, 댓글순</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/c3355682-fc7a-4eb3-a6ef-04ababc95fae"/></p>
         </details><br>
               <details>
         <summary><b>KMS_036_REVIEW_005. 인기 후기글 카테고리 별 목록 조회</b></summary>
                  <br>
         <p><b>  ➡️ 모든 사용자가 후기글을 카테고리별로 추천수가 높은 순으로 조회할 수 있다.<br>
                ➡️ 카테고리 목록 : 과정 후기, 강사 후기<br>
                ➡️ 정렬 조건 : 최신순, 추천순, 조회순, 스크랩순, 댓글순</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/2489b4a4-35ed-4125-bf89-dfa09c5a83a9"/></p>
         </details><br>
                     <details>
         <summary><b>KMS_037_REVIEW_006. 검색어 별 인기 후기글 목록 조회</b></summary>
                  <br>
         <p><b>  ➡️ 모든 사용자가 후기글을 검색어를 입력하여 카테고리별로 추천수가 높은 순으로 조회할 수 있다.<br>
                  ➡️ 검색어 입력<br>
                  ➡️ 카테고리 목록 : 과정 후기, 강사 후기<br>
                  ➡️ 정렬 조건 : 최신순, 추천순, 조회순, 스크랩순, 댓글순</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/cb6f8282-42ae-4375-a52a-a209478f4b0e"/></p>
         </details><br>
	 <details>
         <summary><b>KMS_038_REVIEW_007. 후기 상세 조회</b></summary>
                  <br>
         <p><b> ➡️ 모든 사용자가 후기의 상세 내용을 조회할 수 있다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/600177cf-6b20-41b0-a28c-6a9ff30b93fb"/></p>
         </details><br>
          <details>
         <summary><b>KMS_039_REVIEW_008. 후기 수정</b></summary>
                  <br>
         <p><b> ➡️ 인증회원이 등록한 후기를 수정할 수 있다.<br>
                ➡️ 부트캠프 과정명, 후기 및 평점을 입력하지 않으면 후기가 수정되지 않는다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/2ba89eda-7507-4705-84ba-567d48750e27"/></p>
         </details><br>
                   <details>
         <summary><b>KMS_040_REVIEW_009. 본인 후기글 수정을 위한
상세 조회</b></summary>
                  <br>
         <p><b> ➡️ 인증회원이 등록한 후기를 수정하기 위해 작성한 데이터를 불러올 수 있다.<br>
                ➡️ 후기글 제목, 과정명, 평점, 내용, 사진을 불러온다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/c95ae39f-0ed1-4d9a-8407-2bfe81aedc6e"/></p>
         </details><br>
          <details>
         <summary><b>KMS_041_REVIEW_010. 후기 삭제</b></summary>
                  <br>
         <p><b> ➡️ 인증회원이 등록한 후기를 삭제할 수 있다.<br>
                ➡️ 삭제한 후기는 DB에서 status가 true -> false 로 변경된다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/23427d85-78c3-470a-9040-13c40460932c"/></p>
         </details><br>
</details>
<br>
<details>
<summary><b>🔖 스&nbsp;&nbsp;크&nbsp;&nbsp;랩</b></summary><br>
 <details>
         <summary><b>KMS_042_SCRAP_001. [ 게시글 ] 스크랩 등록</b>
         </summary>
                  <br>
         <p><b>  ➡️ 회원(인증회원, 일반회원) 이 게시글 상세 페이지에서 나중에 다시 보고싶은 게시글을 스크랩할 수 있다.<br>
       ➡️ 스크랩한 게시글은 내 활동내역에서 확인 할 수 있다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/23916785-0d79-4254-84bd-b90c24db5ee6"/></p>
         </details><br>
         <details>
         <summary><b>KMS_043_SCRAP_002. [ 게시글 ] 스크랩 목록 카테고리 별 조회</b>
         </summary>
                  <br>
         <p><b>   ➡️ 회원(인증회원, 일반회원) 이 나의 활동내역에서 스크랩한 게시글 목록을 카테고리별로 조회할 수 있다.<br>
              ➡️ 카테고리 목록 : 지식공유, QnA, 스터디<br>
              ➡️ 비회원은 조회를 할 수 없다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/b46e4e03-2226-4343-aef8-6399eba27280"/></p>
         </details><br>
         <details>
         <summary><b>KMS_044_SCRAP_003. [ 게시글 ] 스크랩 여부 조회</b>
         </summary>
                  <br>
         <p><b>    ➡️ 회원(인증회원, 일반회원) 이 게시글 스크랩을 했는지 여부를 조회할 수 있다.<br>
                    ➡️ 비회원은 조회를 할 수 없다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/5b85af29-5bff-4311-8035-f52ece97ab9d"/></p>
         </details><br>
            <details>
         <summary><b>KMS_045_SCRAP_004. [ 게시글 ] 스크랩 삭제</b>
         </summary>
                  <br>
         <p><b>     ➡️ 회원(인증회원, 일반회원) 이 게시글 상세 페이지에서 게시글에 대한 스크랩을 삭제할 수 있다.<br>
 ➡️ 회원(인증회원, 일반회원) 이 내 활동내역에서 게시글에 대한 스크랩을 삭제할 수 있다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/716194cc-c595-4bf4-990c-cbdafd72e107"/></p>
         </details><br>
              <details>
         <summary><b>KMS_046_SCRAP_005. [ 후기글 ] 스크랩 등록</b>
         </summary>
                  <br>
         <p><b>      ➡️ 회원(인증회원, 일반회원) 이 후기글 상세 페이지에서 나중에 다시 보고싶은 후기글을 스크랩할 수 있다.<br>
 ➡️ 스크랩한 후기글은 내 활동내역에서 확인 할 수 있다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/10f24fd6-6624-4872-bea5-a44da48c013c"/></p>
         </details><br>
          <details>
         <summary><b>KMS_047_SCRAP_006. [ 후기글 ] 스크랩 목록 조회</b>
         </summary>
                  <br>
         <p><b> ➡️ 회원(인증회원, 일반회원) 이 나의 활동내역에서 스크랩한 후기글 목록을 카테고리별로 조회할 수 있다.<br>
                ➡️ 카테고리 목록 : 과정 후기, 강사 후기<br>
                ➡️ 비회원은 조회를 할 수 없다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/a2315106-e192-4e87-8d2c-5c7d7d9bfd95"/></p>
         </details><br>
     <details>
         <summary><b>KMS_048_SCRAP_007. [ 후기글 ] 스크랩 여부 조회</b>
         </summary>
                  <br>
         <p><b> ➡️ 회원(인증회원, 일반회원) 이 후기글 스크랩을 했는지 여부를 조회할 수 있다.<br>
                ➡️ 비회원은 조회를 할 수 없다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/b213201e-03e1-4f02-99c2-8b8229d81e42"/></p>
         </details><br>
          <details>
         <summary><b>KMS_049_SCRAP_008. [ 후기글 ] 스크랩 삭제</b>
         </summary>
                  <br>
         <p><b> ➡️ 회원(인증회원, 일반회원) 이 후기글 상세 페이지에서 후기글에 대한 스크랩을 삭제할 수 있다.<br>
                ➡️ 회원(인증회원, 일반회원) 이 내 활동내역에서 후기글에 대한 스크랩을 삭제할 수 있다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/a4a84222-440f-4c98-a70a-c684ec58db38"/></p>
         </details><br>
                </details>
<br>
<details>
<summary><b>👍 추&nbsp;&nbsp;&nbsp;&nbsp;천</b></summary><br>
              <details>
         <summary><b>KMS_050_UP_001. [ 게시글 ] 추천 클릭</b>
         </summary>
                  <br>
         <p><b> ➡️ 회원(인증회원, 일반회원) 이 게시글에 추천 버튼을 클릭할 수 있다.<br>
                ➡️ 비회원은 추천 버튼을 클릭할 수 없다.
                ➡️ 추천은 1회만 가능하다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/f346d432-a9a8-40ef-b845-cfbf8a756f10"/></p>
         </details><br>
                 <details>
         <summary><b>KMS_051_UP_002. [ 게시글 ] 추천 여부 조회</b>
         </summary>
                  <br>
         <p><b>  ➡️ 회원(인증회원, 일반회원) 이 게시글 추천을 했는지 여부를 조회할 수 있다.<br>
                ➡️ 비회원은 조회를 할 수 없다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/8d55e6ac-bcf4-4efa-bb23-385af6668949"/></p>
         </details><br>
                    <details>
         <summary><b>KMS_052_UP_003. [ 게시글 ] 추천 취소</b>
         </summary>
                  <br>
         <p><b> ➡️ 회원(인증회원, 일반회원) 이 게시글에 클릭한 추천 버튼을 취소할 수 있다.<br>
                ➡️ 비회원은 추천 버튼을 클릭 취소할 수 없다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/16058172-9101-4d41-9ea7-3a5187997190"/></p>
         </details><br>
              <details>
         <summary><b>KMS_053_UP_004. [ 후기글 ] 추천 클릭</b>
         </summary>
                  <br>
         <p><b>  ➡️ 회원(인증회원, 일반회원) 이 후기글에 추천 버튼을 클릭할 수 있다.<br>
                  ➡️ 비회원은 추천 버튼을 클릭할 수 없다.
                  ➡️ 추천은 1회만 가능하다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/c40b4b66-e081-4551-aaee-0a42a8da4110"/></p>
         </details><br>
               <details>
         <summary><b>KMS_054_UP_005. [ 후기글 ] 추천 여부 조회</b>
         </summary>
                  <br>
         <p><b>  ➡️ 회원(인증회원, 일반회원) 이 후기글 추천을 했는지 여부를 조회할 수 있다.<br>
                  ➡️ 비회원은 조회를 할 수 없다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/5e18837a-b271-4913-b4e4-dd5f881b0b18"/></p>
         </details><br>
                    <details>
         <summary><b>KMS_055_UP_006. [ 후기글 ] 추천 취소</b>
         </summary>
                  <br>
         <p><b>   ➡️ 회원(인증회원, 일반회원) 이 후기글에 클릭한 추천 버튼을 취소할 수 있다.<br>
                  ➡️ 비회원은 추천 버튼을 클릭 취소할 수 없다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/8bbc9984-f04e-47da-9fab-c3d370afdca3"/></p>
         </details><br>
        </details>
<br>
<details>
<summary><b>🗨️ 댓&nbsp;&nbsp;&nbsp;&nbsp;글</b></summary><br>
                     <details>
         <summary><b>KMS_056_COMMENT_001. [ 게시글 ] 댓글 작성</b>
         </summary>
                  <br>
         <p><b>   ➡️ 회원(인증회원, 일반회원) 이 게시글에 댓글을 작성할 수 있다.<br>
                  ➡️ 비회원은 댓글을 작성할 수 없다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/628e057d-bc7c-4f56-81ec-55fe247f4f1f"/></p>
         </details><br>
            <details>
         <summary><b>KMS_057_COMMENT_002. [ 게시글 ] 대댓글 작성</b>
         </summary>
                  <br>
         <p><b>     ➡️ 회원(인증회원, 일반회원) 이 게시글 댓글에 대댓글을 작성할 수 있다.<br>
                    ➡️ 비회원은 댓글을 작성할 수 없다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/f6dce0f6-4b94-4487-b657-6b7ca334a3b7"/></p>
         </details><br>
                   <details>
         <summary><b>KMS_058_COMMENT_003. [ 게시글 ] 댓글 조회</b>
         </summary>
                  <br>
         <p><b>  ➡️ 회원(인증회원, 일반회원) 또는 비회원이 게시글에 달린 댓글 목록을 조회할 수 있다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/7ccba43a-1d7f-473d-bc81-b79c4b94f9e8"/></p>
         </details><br>
                     <details>
         <summary><b>KMS_059_COMMENT_004. [ 게시글 ] 댓글 수정</b>
         </summary>
                  <br>
         <p><b>  ➡️ 회원(인증회원, 일반회원) 이 게시글에 등록한 댓글을 수정할 수 있다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/8547a644-c62a-42a0-863a-87f722512038"/></p>
         </details><br>
           <details>
         <summary><b>KMS_060_COMMENT_005. [ 게시글 ] 댓글 삭제</b>
         </summary>
                  <br>
         <p><b>  ➡️ 회원(인증회원, 일반회원) 이 게시글에 등록한 댓글을 삭제할 수 있다.<br>
                ➡️ 삭제한 댓글은 되돌릴 수 없다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/2aaa466f-ee8c-41aa-bb23-520c21fd402a"/></p>
         </details><br>
          <details>
         <summary><b>KMS_061_COMMENT_006. [ 후기글 ] 댓글 작성</b>
         </summary>
                  <br>
         <p><b> ➡️ 회원(인증회원, 일반회원) 이 후기글에 댓글을 작성할 수 있다.<br>
                ➡️ 비회원은 댓글을 작성할 수 없다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/65d95504-cdeb-4c12-b521-bd1e08f6a140"/></p>
         </details><br>
        <details>
         <summary><b>KMS_062_COMMENT_007. [ 후기글 ] 대댓글 작성</b>
         </summary>
                  <br>
         <p><b>  ➡️ 회원(인증회원, 일반회원) 이 후기글 댓글에 대댓글을 작성할 수 있다. <br>
          ➡️ 비회원은 댓글을 작성할 수 없다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/70af2bd9-6790-4597-bb15-64d5ae6be6a8"/></p>
         </details><br>
          <details>
         <summary><b>KMS_063_COMMENT_008. [ 후기글 ] 댓글 조회</b>
         </summary>
                  <br>
         <p><b> ➡️ 회원(인증회원, 일반회원) 또는 비회원이 후기글에 달린 댓글 목록을 조회할 수 있다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/ed97716e-ea85-482f-83fc-fbe4b8c2d46e"/></p>
         </details><br>
          <details>
         <summary><b>KMS_064_COMMENT_009. [ 후기글 ] 댓글 수정</b>
         </summary>
                  <br>
         <p><b>  ➡️ 회원(인증회원, 일반회원) 이 후기글에 등록한 댓글을 수정할 수 있다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/7b515939-c75b-4142-a9db-b64f04b22f45"/></p>
         </details><br>
          <details>
         <summary><b>KMS_065_COMMENT_010. [ 후기글 ] 댓글 삭제</b>
         </summary>
                  <br>
         <p><b> ➡️ 회원(인증회원, 일반회원) 이 후기글에 등록한 댓글을 삭제할 수 있다. <br>
                ➡️ 삭제한 댓글은 되돌릴 수 없다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/2bf5c09a-ce2c-4f59-a626-5fba8a98153c"/></p>
         </details><br>
</details>

<br>
<details>
<summary><b>👑 관리자&nbsp;&nbsp;기능</b></summary><br>
                     <details>
         <summary><b>KMS_066_ADMIN_001. [관리자] 회원가입</b>
         </summary>
                  <br>
         <p><b>   ➡️ 관리자가 [ 이메일, 패스워드, 이름 ] 을 입력하여 회원가입 할 수 있다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/00476ac0-835d-4d36-966b-e65b3cde7482"/></p>
         </details><br>
            <details>
         <summary><b>KMS_067_ADMIN_002. [관리자] 로그인</b>
         </summary>
                  <br>
         <p><b>   ➡️ 관리자가 [ 이메일, 패스워드 ] 를 입력하여 로그인 할 수 있다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/8ceb33f7-2477-421f-8f0d-868a5b10e8c3"/></p>
         </details><br>
              <details>
         <summary><b>KMS_068_ADMIN_003. [관리자] 정보 수정</b>
         </summary>
                  <br>
         <p><b>    ➡️ 관리자가 자신의 정보를 수정할 수 있다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/7d62625c-1b21-4b54-a5bc-3afa74f4c306"/></p>
         </details><br>
              <details>
         <summary><b>KMS_069_ADMIN_004. [관리자] 삭제</b>
         </summary>
                  <br>
         <p><b>   ➡️ 관리자 정보를 삭제할 수 있다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/dd2e973a-1368-4e76-8605-bd0a4c60bfa8"/></p>
         </details><br>
                <details>
         <summary><b>KMS_070_ADMIN_005. 회원 삭제</b>
         </summary>
                  <br>
         <p><b>    ➡️ 관리자가 회원의 상태를 DB에서 false로 변경할 수 있다.<br>
                     ➡️ 회원이 허위 인증을 하거나 과도한 비방, 욕설 등의 게시글, 댓글 등을 남기는 경우 관리자가 비활성화 처리할 수 있다.</b>
</p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/00b76dd9-79e0-4541-9bae-5b984341035b"/></p>
         </details><br>
    <details>
         <summary><b>KMS_071_ADMIN_006. 회원 목록 조회</b>
         </summary>
                  <br>
         <p><b>    ➡️ 관리자가 가입한 회원의 전체 목록을 조회할 수 있다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/c2216c76-d92f-44c5-b40e-e94d1c09bd95"/></p>
         </details><br>
          <details>
         <summary><b>KMS_072_ADMIN_007. 게시판 카테고리 등록</b>
         </summary>
                  <br>
         <p><b>    ➡️ 관리자가 게시판의 카테고리를 등록할 수 있다. ( 지식공유, QnA, 스터디 모집  + 추가 시)</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/4dffd519-1a61-4bcf-9a25-166ff57a5872"/></p>
         </details><br>
          <details>
         <summary><b>KMS_073_ADMIN_008. 게시판 카테고리 목록 조회</b>
         </summary>
                  <br>
         <p><b>   ➡️ 관리자가 게시판의 카테고리 목록을 조회할 수 있다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/b65a3481-0727-4d4c-a243-e11b4ee0ad3d"/></p>
         </details><br>
   <details>
         <summary><b>KMS_074_ADMIN_009. 게시판 카테고리 수정</b>
         </summary>
                  <br>
         <p><b> ➡️ 관리자가 게시판의 카테고리를 수정할 수 있다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/84080870-8263-4f5d-b134-ca896827fdee"/></p>
         </details><br>
          <details>
         <summary><b>KMS_075_ADMIN_010. 게시판 카테고리 삭제</b>
         </summary>
                  <br>
         <p><b> ➡️ 관리자가 게시판의 카테고리를 삭제할 수 있다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/a31ea808-12b0-44de-a88f-3efbb700b97f"/></p>
         </details><br>
                <details>
         <summary><b>KMS_076_ADMIN_011. 후기 카테고리 등록</b>
         </summary>
                  <br>
         <p><b>  ➡️ 관리자가 후기의 카테고리를 등록할 수 있다. ( 과정 후기, 강사 후기 + 추가 시)</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/9be69237-feee-4f4e-9d68-19f06c2e733a"/></p>
         </details><br>
                      <details>
         <summary><b>KMS_077_ADMIN_012. 후기 카테고리 목록 조회</b>
         </summary>
                  <br>
         <p><b>   ➡️ 관리자가 후기의 카테고리 목록을 조회할 수 있다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/3af8f12b-f910-4bc4-8c63-091ca79cf329"/></p>
         </details><br>
                  <details>
         <summary><b>KMS_078_ADMIN_013. 후기 카테고리 수정</b>
         </summary>
                  <br>
         <p><b> ➡️ 관리자가 후기의 카테고리를 수정할 수 있다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/7f61cf59-9b7e-4688-ad75-580c6be2ecb7"/></p>
         </details><br>
                         <details>
         <summary><b>KMS_079_ADMIN_014. 후기 카테고리 삭제</b>
         </summary>
                  <br>
         <p><b>  ➡️ 관리자가 후기의 카테고리를 삭제할 수 있다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/9ebdd77f-6dab-4b29-a531-4b653a62c3a2"/></p>
         </details><br>
                  <details>
         <summary><b>KMS_080_ADMIN_015. 태그 등록</b>
         </summary>
                  <br>
         <p><b>  ➡️ 관리자가 태그를 등록할 수 있다. ( EX : #백엔드 )</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/da067cbc-c460-4f12-867b-7298508e4504"/></p>
         </details><br>
                      <details>
         <summary><b>KMS_081_ADMIN_016. 태그목록 조회</b>
         </summary>
                  <br>
         <p><b>  ➡️ 관리자가 태그 목록을 조회할 수 있다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/177b8be9-4b7f-4e71-8a73-87b0b6b89734"/></p>
         </details><br>
                               <details>
         <summary><b>KMS_082_ADMIN_017. 태그 수정</b>
         </summary>
                  <br>
         <p><b>   ➡️ 관리자가 태그를 수정할 수 있다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/0296b6b5-5fab-41ab-aa16-26dfde97b313"/></p>
         </details><br>
                             <details>
         <summary><b>KMS_083_ADMIN_018. 태그 삭제</b>
         </summary>
                  <br>
         <p><b> ➡️ 관리자가 태그를 삭제할 수 있다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/cd913c63-7051-4a68-8867-645043ef7192"/></p>
         </details><br>
</details>
<br>
<details>
<summary><b>🏷️ 태그&nbsp;&nbsp;기능</b></summary><br>
                     <details>
         <summary><b>KMS_084_HOTTAG_001. 인기 태그 조회</b>
         </summary>
                  <br>
         <p><b>  ➡️ 모든 사용자가 많이 사용된 태그를 5위까지 조회할 수 있다.</b>
       </p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/148943354/8800f5af-3307-410c-ac6b-aad6b3afcb2f"/></p>
         </details><br>
          </details>
