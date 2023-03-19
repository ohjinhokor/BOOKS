//
// Created by jinho on 2023/03/19.
//

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>

#define BUFFER_SIZE 1024

int main(void)
{
    char buf[BUFFER_SIZE];
    int pid;
    int pipe_fd[2];

    pipe(pipe_fd);

    if ((pid = fork()) < 0) {
        fprintf(stderr, "fork error\n");
        exit(1);
    }
    // 부모 프로세스
    else if (pid > 0) {
        printf("PARENT : writing to the pipe\n");
        write(pipe_fd[1], "OSLAB", 6);
        printf("PARENT : wating \n");
        wait(NULL);
        printf("PARENT FINISH");
    }
    // 자식 프로세스
    else {
        printf("CHILD : reading from pipe\n");
        read(pipe_fd[0], buf, 6);
        printf("CHILD : read \"%s \" \n", buf);
        wait(NULL);
        exit(0);
    }

    exit(0);
}
