#include <bits/stdc++.h>
using namespace std;

string cipherText(string str, int n) {
    string cipher = "";
    for (int i = 0; i < str.size(); i++) {
        int temp;
        if (isupper(str[i])) {
            temp = (str[i] - 'A' + n) % 26;
            cipher += char(temp + 'A');
        }
        else if (islower(str[i])) {
            temp = (str[i] - 'a' + n) % 26;
            cipher += char(temp + 'a');
        }
        else {
            cipher += str[i];
        }
    }
    return cipher;
}

string decipherText(string str, int n) {
    string decipher = "";
    for (int i = 0; i < str.size(); i++) {
        int temp;
        if (isupper(str[i])) {
            temp = (str[i] - 'A' - n + 26) % 26;
            decipher += char(temp + 'A');
        }
        else if (islower(str[i])) {
            temp = (str[i] - 'a' - n + 26) % 26;
            decipher += char(temp + 'a');
        }
        else {
            decipher += str[i];
        }
    }
    return decipher;
}

int main() {
    string str;
    cout << "Enter the plain text: ";
    getline(cin, str);

    string cipher = cipherText(str, 25);
    cout << "Cipher-Text: " << cipher << endl;
    string decihper = decipherText(cipher, 25);
    cout << "Decipher-Text: " << decihper << endl;

    return 0;
}