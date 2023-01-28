# Process Management

## 프로세스 생성

- 부모 프로세스(Parent process)가 자식 프로세스(children process) 생성

- 프로세스의 트리(계층 구조) 형성

- 프로세스는 자원을 필요로 함
  - 운영체제로부터 받는다
  - 부모와 공유한다
  
- 자원의 공유
  - 부모와 자식이 모든 자원을 공유하는 모델
  - 일부를 공유하는 모델
  - 전혀 공유하지 않는 모델
- 수행(Execution)
  - 부모와 자식은 공존하며 수행되는 모델
  - 자식이 종료(terminate)될 때까지 부모가 기다리는(wait) 모델


- 주소 공간 (Address space)

  - 자식은 부모의 공간을 복사함(binary and OS data)
  - 자식은 그 공간에 새로운 프로그램을 올림
  
- 유닉스의 예
  - fork() 시스템 콜이 새로운 프로세스를 생성
    - 부모를 그대로 복사 (OS data except PID + binary)
    - 주소 공간 할당
  
  - fork 다음에 이어지는 exec() 시스템 콜을 통해 새로운 프로그램을 메모리에 올림


## 프로세스 종료

- 프로세스가 마지막 명령을 수행한 후 운영체제에게 이를 알려줌(**exit**)

  - 자식이 부모에게 output data를 보냄 (via **wait**).
  - 프로세스의 각종 자원들이 운영체제에게 반납됨
- 부모 프로세스가 자식의 수행을 종료시킴 (abort)
  - 자식이 할당 자원의 한계치를 넘어섬
  - 자식에게 할당된 태스크가 더 이상 필요하지 않음
  - 부모가 종료(exit)하는 경우
    - 운영체제는 부모 프로세스가 종료하는 경우 자식이 더 이상 수행되도록 두지 않는다
    - 단계적인 종료
    
    
    
## fork() 시스템 콜


<img width="615" alt="스크린샷 2023-01-28 오후 5 59 54" src="https://user-images.githubusercontent.com/71378447/215259471-0d515065-62f7-4eb4-b2db-6b61f2c7da3e.png">



## exec() 시스템 콜

<img width="614" alt="스크린샷 2023-01-28 오후 6 04 33" src="https://user-images.githubusercontent.com/71378447/215259474-77b2a91b-1d55-4e61-bc5e-e6c989e18e8c.png">



## wait() 시스템 콜

<img width="611" alt="스크린샷 2023-01-28 오후 6 11 40" src="https://user-images.githubusercontent.com/71378447/215259482-4edd9aa5-5065-4118-9ccf-8da6d69475b9.png">



## exit() 시스템 콜


<img width="611" alt="스크린샷 2023-01-28 오후 6 17 17" src="https://user-images.githubusercontent.com/71378447/215259492-f86107ee-9a9a-4bf7-8ff6-20e228e24a27.png">


### 프로세스와 관련된 시스템 콜

- fork() : create a child(copy)

- exec() : overlay new image(새로운 프로그램으로 덮어 씌움)
- wait() : sleep until child is done
- exit() : frees all the resources, notify parent



## 프로세스 간 협력

- 독립적 프로세스(Independent process)
  - 프로세스는 각자의 주소 공간을 가지고 수행되므로 원칙적으로 하나의 프로세스는 다른 프로세스의 수행에 영향을 미치지 못함
  
- 협력 프로세스(Cooperating process)
  - 프로세스 협력 메커니즘을 통해 하나의 프로세스가 다른 프로세스의 수행에 영향을 미칠 수 있음
  
- 프로세스 간 협력 메커니즘(IPC : Interprocess Communication)

  - 메세지를 전달하는 방법
    - message passing : 커널을 통해 메세지 전달
  - 주소 공간을 공유하는 방법
    - shared memory : 서로 다른 프로세스 간에도 일부 주소 공간을 공휴하게 하는 shared memory 메커니즘이 있음
    - thread : 쓰레드는 사실상 하나의 프로세스이므로 프로세스 간 협력으로 보기는 어렵지만 동일한 process를 구성하는 thread들 간에는 주소 공간을 공유하므로 협력이 가능
    

#### message passing

<img width="610" alt="스크린샷 2023-01-28 오후 6 35 13" src="https://user-images.githubusercontent.com/71378447/215259536-86c67c91-27c9-433c-9d7b-af7768922afd.png">


#### Interprocess Communication

<img width="611" alt="스크린샷 2023-01-28 오후 6 38 45" src="https://user-images.githubusercontent.com/71378447/215259525-c02f8f4c-7637-4663-9d15-9724fcbe0831.png">

