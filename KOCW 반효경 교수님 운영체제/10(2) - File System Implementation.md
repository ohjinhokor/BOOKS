# File System Implementation

## Allocation of File Data in Disk

- Contiguous Allocation

- Linked Allocation

- Indexed Allocation

### Contiguous Allocation

<img width="601" alt="스크린샷 2023-05-25 오후 8 35 58" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/0349e819-d930-49cb-b168-440839ca4270">


- 단점 
  - 외부 단편화가 발생 가능
  - File grow가 어려움(파일 크기를 키우는 것)
    - file 생성시 얼마나 큰 hole을 배당할 것인가?
    - grow 가능 vs 낭비(internal fragmentation)
  
- 장점
  - Fast I/O
  
    - 한 번의 seek/rotation으로 많은 바이트 transfer
    - Realtime file용으로, 또는 이미 run 중이던 process의 swapping용
  - Direct access(=random access)가능
    

### Linked Allocation

<img width="598" alt="스크린샷 2023-05-25 오후 9 03 12" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/4be2ce4a-8db4-46e7-b19f-2f20e72e84cb">

- 장점
  - External fragmentation 발생 안 함.
  
- 단점
  - No random access
  
  - Reliability 문제 (링크드 리스트를 따라가다 중간에 하나라도 bad sector가 있으면, 다음 sector는 접근이 불가능)
    
    - 한 sctor가 고장나 pointer가 유실되면 많은 부분을 잃음
  - Pointer를 위한 공간이 block의 일부가 되어 공간 효율성을 떨어뜨림
    - 512bytes/sector, 4bytes/pointer
    
- 변형
  - File-allocation table(FAT) 파일 시스템
    - 포인터를 별도의 위치에 보관하여 reliability와 공간 효율성 문제 해결
    
    
### Indexed Allocation
<img width="583" alt="스크린샷 2023-05-25 오후 9 03 31" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/9c4bf166-8596-4dfd-8842-fdf3cc71c84c">

- 장점
  - External fragmentation이 발생하지 않음
  - Direct access 가능
  
- 단점
  - small file의 경우 공간 낭비(실제로 많은 file들이 small)
  - Too Large file의 경우 하나의 block으로 index를 저장하기에 부족
    - 해결 방안
      - 1.linked scheme
      - multi-level index
--------
----
      
여기 아래부터는 실제로 파일 시스템이 어떻게 구현되어있는지 살펴볼 것


## UNIX 파일 시스템의 구조

<img width="604" alt="스크린샷 2023-05-25 오후 9 14 37" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/c3be6a5b-d9e1-4d01-af5c-d6cf1750f40d">

<img width="606" alt="스크린샷 2023-05-25 오후 9 15 14" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/eb65b33e-0ae8-43e1-b994-158489bdfd5f">

## Fat File System

<img width="608" alt="스크린샷 2023-05-25 오후 9 21 31" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/f748c82a-cad4-4e46-b19a-137e5aa89ead">



## Free-Space Management
> 위에서 보여준(UNIX 파일 시스템, Fat File System) 파일 시스템에서 비어있는 공간들(free space)를 어떻게 관리할 것인가에 대한 내용

<img width="601" alt="스크린샷 2023-05-25 오후 10 35 53" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/98285e49-172f-4eac-a499-6c602a5ecfa6">

<img width="598" alt="스크린샷 2023-05-25 오후 10 38 41" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/257101b2-c23d-4f31-a031-ec3a0a31d6c0">
