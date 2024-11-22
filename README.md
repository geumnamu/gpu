## GPU 배정

### 서비스 시나리오

기능적 요구상황

1. 고객이 필요한 CPU, GPU, 필요한 저장공간을 선택한다.
2. 고객이 배정 요청을 보낸다.
3. 배정 요청이 되면, 요청 내역이 전달된다.
4. 만약 총 요청한 자원이 할당 받을 수 있는 자원보다 넘어갈 경우, 배정을 취소한다. 
5. 고객이 배정 요청을 취소할 수 있으며, 이 경우 배정을 취소한다.
6. 고객이 배정 여부를 모니터링 할 수 있다.
7. 필요한 자원이 배정됐을 경우, 카톡으로 알림을 보낸다.

비기능적 요구사항

1. 트랜잭션
    1. 고객이 배정받을 수 있는 컴퓨터 자원 이상을 요청할 경우 배정을 하지 못한다. (Sync 호출)
2. 장애격리
    1. 서버에 장애가 생겨 배정을 못하는 상황에도 배정 요청은 계속해서 받을 수 있어야 한다. (Async 호출 [Event Driven], Eventual Consistency)
    2. 컴퓨터 자원이 부족한 경우 배정을 보류한다. (Circuit Breaker, Fallback)
3. 성능
    1. 고객이 컴퓨터 자원 배정 여부를 배정 시스템에서 확인할 수 있어야 한다. (CQRS)
    2. 배정여부가 바뀔 때마다 카톡 등으로 알림을 줄 수 있어야 한다. (Event Driven)

# 클라우드 네이티브 아키텍처 (IaaS)

## MSA 아키텍처 구성도
![image](https://github.com/user-attachments/assets/a32da621-3be5-4dc6-85bf-491c277a7570)

# 클라우드 네이티브 모델링(Biz)

## 도메인분석 - 이벤트스토밍
도메인 - gpu 배정
![image (1)](https://github.com/user-attachments/assets/e678c625-8719-49de-b863-cc3859f3cb76)


# 클라우드 네이비트 개발 MSA (Dev)

## Gateway

### Gateway Configuration
![image (2)](https://github.com/user-attachments/assets/8acc0784-d7bf-4ea7-89e6-28b51e8cb6dd)


### Gateway Testing

To allowanceChecks

![image (3)](https://github.com/user-attachments/assets/377593ab-2027-4d37-8a0c-fbcb35614321)

To allocations

![image (4)](https://github.com/user-attachments/assets/5f50c269-3d62-4ff9-b6d6-09cd2280ec81)
## Saga / Compensation

### 컴퓨터 자원 할당 요청

![image (5)](https://github.com/user-attachments/assets/f1099eeb-74f2-4231-b5ab-14a8a2782d0c)

### 컴퓨터 허용 자원량 확인

![image (6)](https://github.com/user-attachments/assets/8c7a0647-0c83-402c-9cc4-34aa971868a1)

### 컴퓨터 자원 배정됨

- 문제점 : 바로 회수된 후, 배정 - 회수 무한 반복 현상 발견
    - 해결 못함

![image (7)](https://github.com/user-attachments/assets/c7066006-3e5f-4893-baf2-defb8af3613f)

컴퓨터 자원 배정된 후 바로 회수되는 모습

![image (8)](https://github.com/user-attachments/assets/e721ed47-79a1-4c8b-9dea-dbede73e5233)

회수된 컴퓨터 자원 모습

## CQRS

### User Monitoring

![image (9)](https://github.com/user-attachments/assets/374f8ad4-d79c-4a1a-8c2b-0d3ec56b3f52)

![image (10)](https://github.com/user-attachments/assets/7638b89f-4e93-48bd-b521-9eeedb9c20fd)

# 클라우드 네이티브 운영 (Ops, PaaS)

## 클라우드 배포 역량

### 클라우드 배포 - Container 운영

![image (11)](https://github.com/user-attachments/assets/0ef286cc-dac8-40cd-851a-0ab4c4d13eda)

### 컨테이너 형태로 마이크로서비스들이 띄어진 모습

![image (12)](https://github.com/user-attachments/assets/94ddf0cd-4b40-4e14-bc40-1c43adfb6bb7)

### 컨테이너 상태에서 컴퓨터 자원 할당 요청

![image (13)](https://github.com/user-attachments/assets/308f9ecc-5866-431c-bbf2-b3f60a47025c)

### 컴퓨터 허용 자원량 확인

![image (14)](https://github.com/user-attachments/assets/6dd344cc-ae3f-4c44-aeda-9a0c0a3454d8)

## 컨테이너 인프라 설계 및 구성 역량

### 통합 모니터링 - Loggregation/Monitoring

### Jenkins pipeline

![image (15)](https://github.com/user-attachments/assets/a46d1d91-c0ec-418a-95d3-233da83b8e33)


## 오류
PVC와 HPA를 적용해보려고 했으나 한번 kubectl delete svc,pod,deploy --all를 시행한 이후 actuator 오류 발생으로 인해 진행을 하지 못함
