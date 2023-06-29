#include<stdio.h>
#include<stdlib.h>
#include<string.h>

typedef long long ll;

char s[33], ss[40];
int is_valid;

int check(int a, int b) {
    int i = 0, is_valid = 0;
    while(i < 33) {
        if (a == 0 && b == 1) {
            is_valid = 1;
            break;
        }
        if (b > a) {
            b -= a + 1;
            ss[i++] = '0';
        } else if (b <= a && b) {
            a -= b;
            ss[i++] = '1';
        } else {
            break;
        }
    }
    ss[i++] = '0';
    ss[i] = 0;
    return is_valid ? i : -1;
}

int dist(char* s, char *ss) {
    int i, d = 0;
    for(i=0;s[i];i++) d += s[i] != ss[i];
    return d;
}

int main() {

    #ifndef ONLINE_JUDGE
        freopen("testcase.in", "r", stdin);
    #endif

    int t, i, x, ans, l, cur;

    scanf("%d", &t);
    while(t--) {
        scanf("%s%d", s, &x);
        l = strlen(s);
        ans = -1;
        for(i=1;i<=x;i++) {
            if (l == check(x, i)) {
                cur = dist(s, ss);
                if (ans == -1 || cur < ans) {
                    ans = cur;
                }
            }
        }
        if (ans == -1) {
            puts("NO");
            continue;
        }
        puts("YES");
        printf("%d\n", ans);
    }

    return 0;
}