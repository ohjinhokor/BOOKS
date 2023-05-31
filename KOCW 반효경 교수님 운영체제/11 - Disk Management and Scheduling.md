# Disk Management and Scheduling 1


## Disk Structure

- logical block
  - 디스크의 외부에서 보는 디스크의 단위 정보 저장 공간들
  - 주소를 가진 1차원 배열처럼 취급
  - 정보를 전송하는 최소 단위
  
- Sector
  - Logical block이 물리적인 디스크에 매핑된 위치
  - Sector 0은 최외곽 실린더의 첫 트랙에 있는 첫 번째 섹터이다


## Disk Scheduling

<img width="605" alt="스크린샷 2023-05-31 오후 10 06 10" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/20c13441-7a01-4b8a-92f3-6399eafcd8e2">

## Disk Management

 <img width="606" alt="스크린샷 2023-05-31 오후 10 12 05" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/733054e8-066c-412d-adcd-b8639a6815ec">
 
 ## Disk Scheduling Algorithm
 
  - FCFS
 - SSTF
 - SCAN
 - C-SCAN
 - N-SCAN
 - LOOK
 - C-LOOK
 
<img width="568" alt="스크린샷 2023-05-31 오후 10 23 29" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/ab200c72-20fd-48b5-8006-8388e128c558">

<img width="554" alt="스크린샷 2023-05-31 오후 10 24 59" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/87b53e32-1278-4bad-91af-ad659976fe78">

<img width="557" alt="스크린샷 2023-05-31 오후 10 25 53" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/dc8936d3-1c20-4a28-9ca7-57a3a3541e49">

<img width="549" alt="스크린샷 2023-05-31 오후 10 37 41" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/6390a395-6c4b-4c36-90be-e45d278f8588">

<img width="547" alt="스크린샷 2023-05-31 오후 10 38 21" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/a0128635-c301-4a41-9691-a557991fbe52">


## Swap-Space Management

- Disk를 사용하는 두 가지 이유
  - memory의 volatile한 특성 -> file system
  
  - 프로그램 실행을 위한 memory 공간 부족 -> swap space (swap area)


- Swap-space
  - Virtual memory system에서는 디스크를 memory의 연장 공간으로 사용
  - 파일 시스템 내부에 둘 수도 있으나, 별도 partition 사용이 일반적
    - 공간 효율성보다는 속도 효율성이 우선
    - 일반 파일보다 훨씬 짧은 시간만 존재하고 자주 참조됨
    - 따라서, block의 크기 및 저장 방식이 일반 파일 시스템과 다름
    
<img width="593" alt="스크린샷 2023-05-31 오후 10 55 28" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/dbce1fe1-a231-4a1b-bb88-acef832ac520">


## RAID (간단하게만 짚고 넘어감)

<img width="571" alt="스크린샷 2023-05-31 오후 10 57 42" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/65915b95-7fac-4782-97f4-7ee488abdf4c">
