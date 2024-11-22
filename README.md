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


# 클라우드 네이티브 모델링(Biz)

## 도메인분석 - 이벤트스토밍

도메인 - gpu 배정

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/71839517-b71e-4c29-ae42-630c3bf70c0c/e544f0e7-670e-4527-8806-51ec8e3df915/image.png)

# 클라우드 네이비트 개발 MSA (Dev)

## Gateway

### Gateway Configuration

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/71839517-b71e-4c29-ae42-630c3bf70c0c/3bd34cbf-1228-433f-aafc-61c5ffef1f85/image.png)

### Gateway Testing

To allowanceChecks

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/71839517-b71e-4c29-ae42-630c3bf70c0c/b7add87b-f4a7-4f37-a161-fd0f7f65e9df/image.png)

To allocations

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/71839517-b71e-4c29-ae42-630c3bf70c0c/63cca2d4-8c68-4d72-a8fd-5f6ab3e94261/image.png)

## Saga / Compensation

### 컴퓨터 자원 할당 요청

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/71839517-b71e-4c29-ae42-630c3bf70c0c/3d49f1d1-c3bf-4e54-bd42-dfb638e0be70/image.png)

### 컴퓨터 허용 자원량 확인

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/71839517-b71e-4c29-ae42-630c3bf70c0c/33bd2e88-d3ef-4d75-bf86-6492f6912c16/image.png)

### 컴퓨터 자원 배정됨

- 문제점 : 바로 회수된 후, 배정 - 회수 무한 반복 현상 발견
    - 해결 못함

![컴퓨터 자원 배정된 후 바로 회수되는 모습](https://prod-files-secure.s3.us-west-2.amazonaws.com/71839517-b71e-4c29-ae42-630c3bf70c0c/7655872b-75c2-45c5-a4b1-011484fbc960/image.png)

컴퓨터 자원 배정된 후 바로 회수되는 모습

![회수된 컴퓨터 자원 모습](https://prod-files-secure.s3.us-west-2.amazonaws.com/71839517-b71e-4c29-ae42-630c3bf70c0c/884590b3-2ebd-4752-99cf-90e9b5f6686d/image.png)

회수된 컴퓨터 자원 모습

## CQRS

### User Monitoring

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/71839517-b71e-4c29-ae42-630c3bf70c0c/737faa1c-a567-4390-8a02-cb9dc818c12f/image.png)

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/71839517-b71e-4c29-ae42-630c3bf70c0c/a3e6e5f0-f40c-478d-98a6-9f2bc52e5ea9/image.png)

# 클라우드 네이티브 운영 (Ops, PaaS)

## 클라우드 배포 역량

### 클라우드 배포 - Container 운영

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/71839517-b71e-4c29-ae42-630c3bf70c0c/b5e6f046-8296-44db-8809-87fc894f0f6a/image.png)

### 컨테이너 형태로 마이크로서비스들이 띄어진 모습

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/71839517-b71e-4c29-ae42-630c3bf70c0c/3cd1e29c-493b-4824-9f8d-f12ad252cd1d/image.png)

### 컨테이너 상태에서 컴퓨터 자원 할당 요청

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/71839517-b71e-4c29-ae42-630c3bf70c0c/5bca60f3-cfc8-4bca-94ea-aa77873894d4/image.png)

### 컴퓨터 허용 자원량 확인

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/71839517-b71e-4c29-ae42-630c3bf70c0c/0184459c-131f-4147-a173-a6117a4c7c33/image.png)

## 컨테이너 인프라 설계 및 구성 역량

### 통합 모니터링 - Loggregation/Monitoring

### Jenkins pipeline

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/71839517-b71e-4c29-ae42-630c3bf70c0c/754442c0-cc59-4365-b5b0-8d300b28cb99/image.png)
