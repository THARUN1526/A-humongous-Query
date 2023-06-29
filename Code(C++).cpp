#include<bits/stdc++.h>
using namespace std;

int main() {
  cin.tie(0)->sync_with_stdio(0);

  int T; cin >> T;
  while (T--) {
    string s; int X;
    cin >> s >> X;
    int n = s.size();
    for (auto &c: s) c ^= '0';

    int res = n + 1;
    for (int i = 1; i <= X; i++) {
      int A = i, B = X + 1;
      int cr = 0;
      for (int j = n - 1; j > 0; j--) {
        if (B > A) {
          B -= A;
          cr += s[j];
        }
        else {
          A -= B;
          cr += !s[j];
        }
      }

      if (A == 1 && B == 1) res = min(res, cr);
    }
    //cout << (res > n ? "NO": res + !s[0]) << "\n";
    if (res > n) cout << "NO\n";
    else cout << "YES\n" << res << "\n";
  }
  
  return 0;
}