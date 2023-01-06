import { useEffect } from 'react';
import { useRecoilState } from 'recoil';
import { authenticationState } from '../../atoms/AuthenticationAtom';

export default function Logout() {
    const [_, setAuthenticated] = useRecoilState(authenticationState);

    useEffect(() => {
        setAuthenticated(false);
        localStorage.clear("token");
        window.location.href = '/';
    });
}