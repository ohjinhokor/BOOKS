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


## Directory Implementation

![스크린샷 2023-05-31 오후 1 49 38](https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/0830ff61-d7d9-41c1-b826-815eb239cad0)

![스크린샷 2023-05-31 오후 1 53 28](https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/ee8f7f9d-66a9-4fa0-8d29-5e2b7b7ef111)

## VFS and NFS

- Virtual File System(VFS)
  - 서로 다른 다양한 file system에 대해 동일한 시스템 콜 인터페이스(API)를 통해 접근할 수 있게 해주는 OS의 layer
  
- Network File System(NFS)
  - 분산 시스템에서는 네트워크를 통해 파일이 공유될 수 있음
  - NFS는 분산 환경에서의 대표적인 파일 공유 방법임


## Page Cache and Buffer Cache

- Page Cache
  - Virtual memory의 paging system에서 사용하는 page frame을 caching의 관점에서 설명하는 용어
 
- Memory-Mapped I/O
  - File의 일부를 virtual memory에 mapping 시킴
  - 매핑시킨 영역에 대한 메모리 접근 연산은 파일의 입출력을 수행하게 함
  
- Buffer Cache
  - 파일 시스템을 통한 I/O 연산은 메모리의 특정 영역인 buffer cache 사용
  - File 사용의 localty 활용
    - 한 번 읽어온 block에 대한 후속 요청시 buffer cache에서 즉시 전달
  - 모든 프로세스가 공용으로 사용
  - Replacement algorithm 필요 (LRU, LFU 등)
 
 
- Unified Buffer Cache
  - 최근의 OS에서는 기존의 buffer cache가 page cache에 통합됨

![스크린샷 2023-05-31 오후 2 27 11](https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/a4c6b68c-57fe-4a3c-8aab-7e5f979e788d)

![스크린샷 2023-05-31 오후 2 28 31](https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/60868d11-9cd0-420b-982f-4702b8655558)


