<br>

# Badges
<div align="left">
&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://img.shields.io/badge/GitHub-181717?style=flat&logo=GitHub&logoColor=white&color=black"></a></a>
&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://img.shields.io/badge/Git-F05032?style=flat&logo=Git&logoColor=white&color=ffa500"></a></a>
&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://img.shields.io/badge/GitHub Actions-2088FF?style=flat&logo=GitHub Actions&logoColor=white&color=gray"></a></a> <br>
&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://img.shields.io/badge/Jenkins-D24939?style=flat&logo=jenkins&logoColor=white"/></a></a>
&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://img.shields.io/badge/Docker-2496ED?style=flat&logo=Docker&logoColor=black&color=blue"/></a></a>
&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://img.shields.io/badge/Kubernetes-326CE5?style=flat&logo=Kubernetes&logoColor=blue&color=skyblue"/></a></a> <br>
&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://img.shields.io/badge/Selenium-C21325?style=flat&logo=Selenium&logoColor=black&color=green"/></a></a>
&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://img.shields.io/badge/Jest-C21325?style=flat&logo=Jest&logoColor=black&color=orange"/></a></a> <br>
&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://img.shields.io/badge/Slack-4A154B?style=flat&logo=Slack&logoColor=yellow&color=purple"/></a></a>
</div>

<br>
<br>
<br>
<br>

# 🖥️ Architecture
<details>
<summary>system architecture</summary>
<img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/105422037/b783b71b-e6dd-40b4-8973-0bfa57979a16">
</details>

<br>
<details>
<summary>cluster architecture</summary>
<img src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/105422037/db082524-56c8-4e6a-98ae-953167014d56">
</details>


<br>
<br>
<br>
<br>

# Devops 운영 환경
<details>
<summary>DockerHub Images</summary>

<h3>백엔드</h3>
<img width="500" alt="docker hub backend" src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/105422037/e6d1b2ca-2209-4b93-8c93-a0b1d7fde036">

<br>

<h3>프론트엔드</h3>
<img width="500" alt="docker hub frontend" src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/105422037/8d33cccb-fdc8-4995-94a2-c5fabced2f8b">

<br>
</details>

<br>
<br>

<details>
<summary>Kubernetes</summary>
<img width="1725" alt="스크린샷_2024-03-25_오후_2 06 16" src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/105422037/7ec7a8f6-4a06-48a1-868b-c8b15858d658">

<br>

<img width="1274" alt="스크린샷 2024-03-26 오후 4 18 44" src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/105422037/cd7c2cd4-6199-45e5-83fd-75bc8fb8e5d3">

</details>

<br>
<br>

<details>
<summary>Jenkins</summary>
<img width="1266" alt="스크린샷 2024-03-26 오후 4 13 45" src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/105422037/71de888b-96e9-4752-94cf-13e5199d8321">
</details>

<br>
<br>
<br>
<br>

# CI/CD 시나리오
<details>
<summary>CI</summary>

<h3>백엔드</h3>

#### 1. ```Github```  원격 저장소의 개발 중인 브랜치에 ```push``` <br>

#### 2. 개발 중이던 브랜치에서 ```Github```  ```develop``` 브랜치에 ```pull request``` <br>

#### 3. ```pull request```가 완료됨과 동시에 ```Github Webhook```을 통해 ```Jenkins```에 알림 <br>

#### 4. ```Jenkins```가 푸쉬된 새 코드를 가져오기 위해 ```git clone``` <br>

#### 5. ```Jenkins```는 ```mvn```을 통해 컴파일 및 패키징 <br>

#### 6. ```Jenkins```는 ```mvn```을 통해 테스트 코드 실행 <br>

#### 7. 테스트 코드 실행 전후로```Slack```의 ```'#buildup-dev'``` 채널로 알림 전송 (성공, 실패) <br>


<br>
<br>

<h3>프론트엔드</h3>

#### 1. ```Github```  원격 저장소의 개발 중인 브랜치에 ```push``` <br>

#### 2. 개발 중이던 브랜치에서 ```Github```  ```develop``` 브랜치에 ```pull request``` <br>

#### 3. ```pull request```가 완료됨과 동시에 ```Github Webhook```을 통해 ```Jenkins```에 알림 <br>

#### 4. ```Jenkins```가 푸쉬된 새 코드를 가져오기 위해 ```git clone``` <br>

#### 5. ```Jenkins```는 ```npm```을 통해 package 설치 <br>

#### 6. ```Jenkins```는 기존에 생성되어 있던 ```dist``` 폴더 삭제 <br>

#### 7. 빌드 전후로 ```Slack```의 ```'#buildup-dev'``` 채널로 알림 전송 (성공, 실패) <br>

#### 8. ```Jenkins```는 ```npm```을 통해 ```build``` <br>

#### 9. 테스트 코드 실행 전후로 ```Slack```의 ```'#buildup-dev'``` 채널로 알림 전송 (성공, 실패) <br>

</details>

<br>

<details>
<summary>CD</summary>

<h3>백엔드 / 프론트엔드</h3>

#### 1. ```Jenkins```는 ```Docker Build``` 단계에서 새로운 ```Docker Image```를 ```Build```하고 ```tag``` 지정 (2.x) <br>

#### 2. ```Docker Image``` 빌드 전후로 ```Slack```의 ```'#buildup-dev'``` 채널로 알림 전송 (성공, 실패) <br>

#### 3. ```Build```된 ```Docker Image```는 ```Docker Push```를 통해 ```Docker Hub```로 ```push``` **[hyungdoyou/bootshelf-fe]** <br>

#### 4. ```Docker Push``` 전후로 ```Slack```의 ```'#buildup-dev'``` 채널로 알림 전송 (성공, 실패) <br>

#### 5. ```Jenkins```는 미리 등록되어 있던 ```ssh``` server 설정을 이용해 ```K8S Cluster```의 ```master``` 서버에 ```deployment.yaml``` update <br>

#### 6. update된 Deployment 파일은 ```kubectl``` 명령어를 통해 ```K8S Cluster```에 적용 <br>

#### 7. deployment.yaml 전송 후 ```Slack```의 ```'#buildup-dev'``` 채널로 알림 전송 (성공, 실패) <br>

#### 8. ```Jenkins``` Pipeline 종료 후 ```Slack```의 ```'#buildup-dev'``` 채널로 최종 알림 전송 <br>

<br>

</details>

<br>
<br>
<br>
<br>

# 시연 화면
<details>
<summary>Frontend</summary>
<video width="1000" controls>
  <source src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/105422037/4ec0308c-dda8-47f2-ae13-e4634e637914" type="video/mp4">
</video>

</details>

<br>

<details>
<summary>Backend</summary>
<video width="1000" controls>
  <source src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/105422037/f07742cb-3dc0-4e23-a927-657bfdc70245" type="video/mp4">
</video>
</details>

<br>

<details>
<summary>Slack Alarm</summary>

<img width="700" alt="slack alarm" src="https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/105422037/c4139122-e358-4627-87fb-f4753d73e944">

</details>

<br>
<br>