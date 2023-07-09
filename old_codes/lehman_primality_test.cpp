// #include<bits/stdc++.h>
// #define ll long long
// using namespace std;

// ll bigmod(ll n, ll r, ll m){
//     if(r == 0) return 1;
//     if(r == 1) return n%m;
//     ll x;
//     if(r%2==1) x = bigmod(n, r-1, m)*n;
//     else{
//         x = bigmod(n, r/(ll)2, m);
//         x = x*x;
//     }
//     return x%m;
// }

// // is prime using lehamans algorithm
// bool isPrime(ll p, int iter){
//     if(p <= 1 || p%2 == 0) return false;
//     if(p == 2 || p == 3) return true;

//     ll a, x;
//     for(int i = 0; i < iter; i++){
//         a = 2 + rand()%(p-3);
//         x = bigmod(a, p-1, p);
//         if(x != 1 && x != -1 && x != (p-1)) return false;
//     }
//     return true;
// }

// int main(){

//     ll p;
//     cin>>p;

//     if(isPrime(p, 100)){
//         puts("Prime!");
//     }
//     else{
//         puts("Not Prime !");
//     }

//     return 0;
// }




#include <bits/stdc++.h>
using namespace std;

long long power(long long a, long long temp) {
    long long result = a;
    temp--;
    while(temp > 0) {
        result *= a;
        temp--;
    }
    return result;
}


bool primeCheck(long long n, int iter) {
    long long a, res, x, temp;
    temp = (n-1)/2;
    for (int i = 0; i < iter; i++) {
        a = (2  + rand()) % (n-1);
        x = power(a, temp) % n;
        cout << a << "    " << x << endl;
        if (x != 1 && x - n == -1) {
            return false;
        }
    }
    return true;
}

int main () {

    long long n;
    cout << "Enter a number: ";
    cin >> n;

    if (n < 2 || n % 2 == 0) {
        cout << "Not a prime number" << endl;
    } else {
        if (primeCheck(n, 100)) {
            cout << "May be a prime number." << endl;
        } else {
            cout << "Not a prime number" << endl;
        }
    }

    return 0;
}