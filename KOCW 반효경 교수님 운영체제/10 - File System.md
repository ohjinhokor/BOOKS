# File Systems

## File and File System

- File
  - A name collection of related information
  - 일반적으로 비휘발성의 보조기억장치에 저장
  - 운영체제는 다양한 저장 장치를 file이라는 동일한 논리적 단위로 볼 수 있게 해 줌
  
  - Operation
    - create, read, write, reposition (lseek), delete, open, close 등
    
- File attribute (혹은 파일의 metadata)
  - 파일 자체의 내용이 아니라 파일을 관리하기 위한 각종 정보들
    - 파일 이름, 유형, 저장된 위치, 파일 사이즈
    - 접근 권한(읽기, 쓰기, 실행), 시간(생성, 변경, 사용), 소유자 등
    
- File System
  - 운영 체제에서 파일을 관리하는 부분
  
  - 파일 및 파일의 메타데이터, 디렉토리 정보 등을 관리
  - 파일의 저장 방법 결정
  - 파일 보호 등
  
## Directory and Logical Disk

- Directory
  - 파일의 메타데이터 중 일부를 보관하고 있는 일종의 특별한 파일
  - 그 디렉토리에 속한 파일 이름 및 파일 attribute
  - operation
    - search for a file, create a file, delete a file
    - list a directory, rename a file, traverse the file system
    
- Partition (=Logical Disk)

  - 하나의 (물리적)디스크 안에 여러 파티션을 나누는게 일반적

  - 여러 개의 물리적인 디스크를 하나의 파티션으로 구성하기도 함
  - (물리적) 디스크를 파티션으로 구성한 뒤, 각각의 파티션에 **file system**을 깔거나 **swapping** 등 다른 용도로 사용할 수 있음
  

## Open()

<img width="602" alt="스크린샷 2023-05-25 오후 8 04 00" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/6c345802-e4df-4dd4-a0d1-ea9cf0da7c9e">

<img width="610" alt="스크린샷 2023-05-25 오후 8 04 54" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/9de4021a-c327-4869-9d48-278e80ddc2ac">

<img width="595" alt="스크린샷 2023-05-25 오후 8 15 40" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/a2c6e83b-4b39-4bcb-a636-77bdda5f0172">

## File Protection

<img width="598" alt="스크린샷 2023-05-25 오후 8 18 14" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/15e681c4-afb0-4b0d-a59a-4de36e1f92a4">

## File System의 Mounting

<img width="603" alt="스크린샷 2023-05-25 오후 8 24 58" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/a8369ef2-92bc-4044-8f17-7185833e2043">

-> 마운팅으로 해결함

<img width="607" alt="스크린샷 2023-05-25 오후 8 26 14" src="https://github.com/ohjinhokor/BOOKS-and-LECTURES/assets/71378447/5d599025-fbe9-4c60-8235-127b73f2295b">

-> 위에 사진을 예로 들면, usr에 접근하면 disk3의 root directory에 접근하는 꼴이 되는 것임

## Access Methods

- 시스템이 제공하는 파일 정보의 접근 방식
  - 순차 접근(sequential access)
  
    - 카세트 테이프를 사용하는 방식처럼 접근
    - 읽거나 쓰면 offset은 자동적으로 증가
  - 직접 접근(direct access, random access)
    - LP 레코드 판과 같이 접근하도록 함
    - 파일을 구성하는 레코드를 임의의 순서로 접근할 수 있음
