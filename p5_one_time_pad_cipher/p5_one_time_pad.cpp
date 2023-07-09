#include <bits/stdc++.h>
using namespace std;

string cipherText(string str, string key) {
    string cipher = "";

    for (int i = 0; i < str.size(); i++) {
        char ch;
        if (isupper(str[i])) {
            ch = (str[i] - 'A' + key[i] - 'A') % 26 + 'A';
        }
        else if (islower(str[i])) {
            ch = (str[i] - 'a' + key[i] - 'A') % 26 + 'a';
        }
        else {
            ch = str[i];
        }
        cipher += ch;
    }

    return cipher;
}

string decipherText(string str, string key) {
    string decipher = "";

    for (int i = 0; i < str.size(); i++) {
        char ch;
        if (isupper(str[i])) {
            ch = (str[i] - 'A' - key[i] + 'A' + 26) % 26 + 'A';
        }
        else if (islower(str[i])) {
            ch = (str[i] - 'a' - key[i] + 'A' + 26) % 26 + 'a';
        }
        else {
            ch = str[i];
        }
        decipher += ch;
    }

    return decipher;
}

int main() {
    ifstream input;
    // input.open("p5_pad.txt");
    input.open("p5_random_string_pad.txt");

    string text = "DEPARTMENT OF COMPUTER SCIENCE AND ENGINEERING";
    string key;
    getline(input, key);

    string cipher = cipherText(text, key);
    string decipher = decipherText(cipher, key);

    cout << "Plain-Text: " << text << endl;
    cout << "Cipher-Text: " << cipher << endl;
    cout << "Decipher-Text: " << decipher << endl;

    input.close();
    return 0;
}