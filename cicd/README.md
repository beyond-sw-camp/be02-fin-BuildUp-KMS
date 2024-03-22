<br>

![header](https://capsule-render.vercel.app/api?type=Waving&color=541D7A&height=250&section=header&text=BOOTSHELF🌠&desc=BOOTCAMP&descSize=20&descAlign=68&descAlignY=70&fontSize=100&animation=fadeIn&fontColor=ffff)

> # **[플레이 데이터] 한화시스템 BEYOND SW캠프 / 📚 Team : BuildUp 📚**

<br>

### 🤼‍♂️ 팀원 소개

<br><br>

&nbsp;　&nbsp;　&nbsp;　&nbsp;　&nbsp;　&nbsp;　&nbsp;　&nbsp;　 🦁 **[유형도](https://github.com/hyungdoyou)**&nbsp;　 🐻 **[임태우](https://github.com/Tesssssssssy)** &nbsp;　 🐶 **[홍현주](https://github.com/hyeonjju)** &nbsp;　 🐱 **[강지흔](https://github.com/heueun)** &nbsp;　 🐼 **[길민석](https://github.com/gilms0730)**
<br><br><br><br><br>

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

### [system architecture](https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/105422037/8b43264d-ded8-4656-b779-532fc8a254d0)

### [Cluster Architecture](https://github.com/beyond-sw-camp/be02-fin-BuildUp-KMS/assets/105422037/db082524-56c8-4e6a-98ae-953167014d56)


<br>
<br>
<br>
<br>

# Devops 운영 환경
<details>
<summary style="font-size: 18px; font-weight: bold; color: purple;">DockerHub Images</summary>

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
<summary style="font-size: 18px; font-weight: bold; color: purple;">Kubernetes</summary>

</details>

<br>
<br>

<details>
<summary style="font-size: 18px; font-weight: bold; color: purple;">Jenkins</summary>

</details>

<br>
<br>
<br>
<br>

# CI/CD 시나리오
<details>
<summary style="font-size: 18px; font-weight: bold; color: purple;">CI</summary>

<h3>백엔드</h3>

#### 1. <img src="https://img.shields.io/badge/GitHub-181717?style=flat&logo=GitHub&logoColor=white&color=black">  원격 저장소의 개발 중인 브랜치에 ```push``` <br>

#### 2. 개발 중이던 브랜치로부터 <img src="https://img.shields.io/badge/GitHub-181717?style=flat&logo=GitHub&logoColor=white&color=black">  ```develop``` 브랜치에 ```pull request``` <br>

#### 3. ```pull request```가 완료됨과 동시에 <img src="https://img.shields.io/badge/GitHub Actions-2088FF?style=flat&logo=GitHub Actions&logoColor=white&color=gray">으로 <img src="https://img.shields.io/badge/Jenkins-D24939?style=flat&logo=jenkins&logoColor=white"/>에 알림 <br>

#### 4. <img src="https://img.shields.io/badge/Jenkins-D24939?style=flat&logo=jenkins&logoColor=white"/>가 푸쉬된 새 코드를 가져오기 위해 ```git clone``` <br>

#### 5. <img src="https://img.shields.io/badge/Jenkins-D24939?style=flat&logo=jenkins&logoColor=white"/>는 ```Build``` 단계에서 ```mvn```을 통해 컴파일 및 빌드, 패키징 <br>

#### 6. 빌드 전후로 <img src="https://img.shields.io/badge/Slack-4A154B?style=flat&logo=Slack&logoColor=yellow&color=purple"/>의 ```'#buildup-dev'``` 채널로 알림 전송 (성공, 실패) <br>

<br>
<br>

<h3>프론트엔드</h3>

#### 1. <img src="https://img.shields.io/badge/GitHub-181717?style=flat&logo=GitHub&logoColor=white&color=black">  원격 저장소의 개발 중인 브랜치에 ```push``` <br>

#### 2. 개발 중이던 브랜치로부터 <img src="https://img.shields.io/badge/GitHub-181717?style=flat&logo=GitHub&logoColor=white&color=black">  ```develop``` 브랜치에 ```pull request``` <br>

#### 3. ```pull request```가 완료됨과 동시에 <img src="https://img.shields.io/badge/GitHub Actions-2088FF?style=flat&logo=GitHub Actions&logoColor=white&color=gray">으로 <img src="https://img.shields.io/badge/Jenkins-D24939?style=flat&logo=jenkins&logoColor=white"/>에 알림 <br>

#### 4. <img src="https://img.shields.io/badge/Jenkins-D24939?style=flat&logo=jenkins&logoColor=white"/>가 푸쉬된 새 코드를 가져오기 위해 ```git clone``` <br>

#### 5. <img src="https://img.shields.io/badge/Jenkins-D24939?style=flat&logo=jenkins&logoColor=white"/>는 ```Build``` 단계에서 ```npm```을 통해 dependency 설치, 컴파일, 빌드 <br>

#### 6. 빌드 전후로 <img src="https://img.shields.io/badge/Slack-4A154B?style=flat&logo=Slack&logoColor=yellow&color=purple"/>의 ```'#buildup-dev'``` 채널로 알림 전송 (성공, 실패) <br>

</details>

<br>

<details>
<summary style="font-size: 18px; font-weight: bold; color: purple;">CD</summary>
진행중입니다
<h3>백엔드</h3>

#### 1. Jenkins는 Docker Build 단계에서 새로운 Docker Image를 Build하고 tag 지정 (2.x) <br>

#### 2. Build된 Docker Image는 Docker Push를 통해 Docker Hub로 push **[hyungdoyou/bootshelf-fe]** <br>

#### 3. Jenkins는 미리 등록한 K8S Cluster의 master 서버에 ssh로 Deployment를 update <br>

#### 4. update된 Deployment 파일은 kubectl 명령어를 통해 K8S Cluster에 적용 <br>

#### 5. 배포 성공, 실패 시 Slack의 ```'#buildup-dev'``` 채널로 알림 전송 <br>

<br>

<h3>프론트엔드</h3>

#### 1. Jenkins는 Docker Build 단계에서 새로운 Docker Image를 Build하고 tag 지정 (2.x) <br>

#### 2. Build된 Docker Image는 Docker Push를 통해 Docker Hub로 push **[hyungdoyou/bootshelf-fe]** <br>

#### 3. Jenkins는 미리 등록한 K8S Cluster의 master 서버에 ssh로 Deployment를 update <br>

#### 4. update된 Deployment 파일은 kubectl 명령어를 통해 K8S Cluster에 적용 <br>

#### 5. 배포 성공, 실패 시 Slack의 '#buildup-dev' 채널로 알림 전송 <br>

</details>

<br>
<br>
<br>
<br>

# CI/CD 기대 효과
<details>
<summary style="font-size: 18px; font-weight: bold; color: purple;">CI</summary>
코드를 변경했을 때 자동으로 Build되어 빠르고 안정적으로 코드를 통합할 수 있고
항상 배포 준비가 완료된 상태를 유지할 수 있다. 

</details>

<br>

<details>
<summary style="font-size: 18px; font-weight: bold; color: purple;">CD</summary>
개발자가 Github의 remote repository에 code를 push할 때마다 자동으로 빌드 및 배포되기 때문에
수동 배포에 비해 시간과 노력을 크게 절약할 수 있고 사용자에게 즉각적인 업데이트를 제공할 수 있다. 

</details>

<br>
<br>

# 시연 화면
<details>
<summary style="font-size: 18px; font-weight: bold; color: purple;">Frontend</summary>

</details>

<br>

<details>
<summary style="font-size: 18px; font-weight: bold; color: purple;">Backend</summary>

</details>

<br>

<details>
<summary style="font-size: 18px; font-weight: bold; color: purple;">Slack Alarm</summary>

</details>

<br>
<br>