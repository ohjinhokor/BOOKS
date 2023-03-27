#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/types.h>

struct stat statbuf;

void ssu_checkfile(char *fname, time_t *time);

int main(int argc, char *argv[])
{
    time_t intertime;

    if (argc != 2) {
        fprintf(stderr, "usage: %s <file〉\n", argv[0]);
        exit(1);
    }

    if (stat(argv[1], &statbuf) < 0) {
        fprintf(stderr, "stat error for %s\n", argv[1]);
        exit(1);
    }

    intertime = statbuf.st_mtime;

    while (1) {
        ssu_checkfile(argv[1], &intertime);
        sleep(10);
    }

}


void ssu_checkfile (char *fname, time_t *time) {
    if (stat(fname, &statbuf) < 0) {
        fprintf(stderr, "Warning : ssu_checkfile() error!\n");
        exit(1);
    } else {
        if (statbuf.st_mtime != *time) {
            printf("Warning : %s was modified!.\n", fname);
            *time = statbuf.st_mtime;
        }
    }
}
