//
// Created by jinho on 2023/03/16.
//

#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>

int main(void) {
    // 교수님 질문 : char *로 문자열을 받으면 fname이라는 변수는 어디를 가리키고 있을까?
    // fname이라는 변수 자체는 스택 프레임에 저장되어있고, 가리키고 있는 주소는 문자열이 저장되어있는 주소의 0번지이다.
    // fname : open할 텍스트 파일의 이름
    char *fname = "ssu_test.txt";
    // open 함수의 결과가 성공인지 실패인지 나타내는 변수
    int fd;

    if ((fd = open(fname, O_RDONLY)) < 0) {
        // open 함수가 실패한 경우
        fprintf(stderr, "open error for %s\n", fname);
        exit(1);
    } else
        // open함수가 성공한 경우
        printf("Success!\nFilename : %s\nDescriptor : %d\n", fname, fd);

    exit(0);
}
