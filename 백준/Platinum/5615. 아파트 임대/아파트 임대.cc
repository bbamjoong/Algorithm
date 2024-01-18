#include <iostream>
using namespace std;
 
typedef unsigned long long ull;
 
ull power(ull x, ull y, ull p) {
    ull res = 1;
    x = x % p;
    while (y > 0) {
        if (y & 1) {
            res = (res * x) % p;
        }
        y = y >> 1;
        x = (x * x) % p;
    }
    return res;
}
 
bool miller_rabin(ull n, ull a) {
    ull r = 0;
    ull d = n - 1;
    while (d % 2 == 0) {
        r++;
        d = d >> 1;
    }
    ull x = power(a, d, n);
    if (x == 1 || x == n - 1) return true;
    for (int i = 0; i < r - 1; i++) {
        x = power(x, 2, n);
        if (x == n - 1) return true;
    }
    return false;
    
 
}
 
bool isPrime(ull n) {
    ull alist[5] = { 2, 3, 5, 7, 11 };
    if (n <= 1) return false;
    if (n == 2 || n == 3) return true;
    if (n % 2 == 0) return false;
    for (int i = 0; i < 5; i++) {
        ull a = alist[i];
        if (n == a) return true;
        if (!miller_rabin(n, a)) return false;
    }
    return true;
 
}
 
int main(void) {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int n, cnt = 0;
    cin >> n;
    while (n--) {
        ull s;
        cin >> s;
        if(isPrime(2*s+1)) cnt++;
    }
    cout <<cnt;
    return 0;
}