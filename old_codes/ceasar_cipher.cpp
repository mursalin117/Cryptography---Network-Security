#include <bits/stdc++.h>
using namespace std;


string ciperText(string str, int shift) {
    string res = "";
    for (int i = 0; i < str.size(); i++) {
        if (islower(str[i])) {   //str[i] >= 'a' && str[i] <= 'z') {
            res += (char) (str[i] - 'a' + shift) % 26 + 97;    
        }
        else if (isupper(str[i])) {       //str[i] >= 'A' && str[i] <= 'Z') {
            res += (char) (str[i] - 'A' + shift) % 26 + 65;
        }
        // res += (char) (str[i] + shift) % 255;
        else res += str[i];
    }
    // cout << res.size() << endl;
    return res;
}

string decipherText(string str, int shift) {
    string res = "";
    for (int i = 0; i < str.size(); i++) {
        if (islower(str[i])) {  //str[i] >= 'a' && str[i] <= 'z') {
            res += (char) (str[i] - 'a' - shift + 26) % 26 + 97; 
            // cout << (str[i] - 'a' - shift + 26 ) + 97 << endl;
        } 
        else if (isupper(str[i])) {   //str[i] >= 'A' && str[i] <= 'Z') {
            res += (char) (str[i] - 'A' - shift + 26) % 26 + 65;
        }
        else res += str[i];
        // res += (char) (str[i] - shift) % 255;
    }
    // cout << res.size() << endl;
    return res;
}

int main(int argc, char const *argv[]){
    string str;
    cout << "Enter the plain text: ";
    getline(cin, str);
    cout << endl;
    
    cout << "Enter the no of shift: ";
    int n; 
    cin >> n;
    
    string ciTxt = ciperText(str, n);
    cout << "Cipher Text: " << ciTxt << endl;
    string deciTxt = decipherText(ciTxt, n);
    cout << "Decipher Text: " << deciTxt << endl;

    return 0;
}
