# CPU Scheduling

## CPU and I/O Bursts in Program Execution

- CPU 만 사용하고 있는 단계를 CPU burst라고 하고, I/O만 사용하고 있는 단계를 I/O burst라고 한다.

<img width="611" alt="스크린샷 2023-01-28 오후 6 49 22" src="https://user-images.githubusercontent.com/71378447/215261160-73e393f0-f657-4ea6-8676-7fcc52fcd620.png">



## CPU-burst Time의 분포

- 아래 사진에서 알 수 있는 점은 CPU만 계속 사용하는 프로그램은 소수이고 대부분이 I/O 작업과 cpu 작업이 번갈아 일어나는 경우(= I/O bound job) 가 훨씬 많았다는 것이다

<img width="608" alt="스크린샷 2023-01-28 오후 6 52 22" src="https://user-images.githubusercontent.com/71378447/215261231-7a257843-dfdd-4d89-9f45-78e7d92afa25.png">


- 여러 종류의 job(=process)가 섞여 있기 때문에 CPU 스케줄링이 필요하다

  - Interactive job에게 적절한 reponse 제공 요망
  - CPU와 I/O 장치 등 시스템 자원을 골고루 효율적으로 사용
  

## 프로세스의 특성 분류

- 프로세스는 그 특성에 따라 다음 두 가지로 나눔
  - I/O bound process
    - CPU를 잡고 계산하는 시간보다 I/O에 많은 시간이 필요한 job
    - (many short CPU bursts)
  
  
  - CPU-bound process
    - 계산 위주의 job
    - (few very long CPU bursts)
    
## CPU scheduler & Dispatcher

- CPU scheduler와 Dispatcher는 운영체제 안에 코드로 작성되어있음.

<img width="611" alt="스크린샷 2023-01-28 오후 7 06 22" src="https://user-images.githubusercontent.com/71378447/215261238-1780b383-1f55-46cf-bce7-5db70b6ee3c4.png">
