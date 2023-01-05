import React from 'react';
import { Link, Outlet } from 'react-router-dom';
import { useRecoilState } from 'recoil';
import { authenticationState } from '../atoms/AuthenticationAtom';
import './ProtectedRoute.css';

const ProtectedRoute = () => {
    const [authenticated, setAuthenticated] = useRecoilState(authenticationState);

    if (!authenticated) {
        window.location.href = '/signin';
    }

    return (
        <main>
            <nav>
                <section id='nav-left'>
                    <h2>TodoApp</h2>
                    <Link className='nav-btn' to='/tasks/new'>New Task</Link>
                </section>
                <section id='nav-right'>
                    <Link className='nav-btn' to='/logout'>Logout</Link>
                </section>
            </nav>

            <Outlet/>

            <footer>
                <span>Michał Skreczko &copy; 2023</span>
            </footer>
        </main>
    )
}

export default ProtectedRoute;