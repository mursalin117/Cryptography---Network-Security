#include <bits/stdc++.h>
using namespace std;

string cipherText(string text, string key) {
    
    string cipher = "";
    for (int i = 0; i < text.size(); i++) {
        char ch;
        if (isupper(text[i])) {
            ch = (text[i] - 'A' + key[i] - 'A') % 26 + 'A';
        } else if (islower(text[i])) {
            ch = (text[i] - 'a' + key[i] - 'A') % 26 + 'a';
        }
        cipher += ch;
        // cout << (int)ch << endl;
    }
    // cout << cipher << endl;
    return cipher;
} 

string decipherText(string cipher, string key) {
    string decipher = "";
    for (int i = 0; i < cipher.size(); i++) {
        char ch;
        if (isupper(cipher[i])) {
            ch = (cipher[i] - 'A' - key[i] + 'A' + 26) % 26 + 'A';
        } else if (islower(cipher[i])) {
            ch = (cipher[i] - 'a' - key[i] + 'A' + 26) % 26 + 'a';
        }
        decipher += ch;
        // cout << (int)ch << endl;
    }
    // cout << decipher << endl;
    return decipher;
}

int main() {
    string text = "HellO";
    string key = "MONEY";
    cout << text << endl;    
    string cipher = cipherText(text, key);
    cout << cipher << endl;
    string decipher = decipherText(cipher, key);
    cout << decipher << endl;
    return 0;
}