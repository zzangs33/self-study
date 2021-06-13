import React, {Component} from 'react';

function solution(A, B) {
    let res = 1;

    while (res * (res + 1) < A) {
        res++;
    }

    let count = 0;
    for (let i = res; i * (i + 1) < B; i++) {
        count++;
    }

    return count;
}

function typeCheck(object) {
    const validate = (type, value) => {
        switch (type) {
            case'string':
                if (typeof value !== 'string') throw new Error();
                break;
            case 'number':
                if (typeof value !== 'number') throw new Error();
                break;
            case 'int':
                if (!Number.isInteger(value)) throw new Error();
                break;
            case'float':
                if (typeof value !== 'number' || Number.isInteger(value)) throw new Error();
                break;
            case 'bool':
                if (typeof value !== 'boolean') throw new Error();
                break;
            default:
        }
    }
    const getType = (key) => {
        if (!key.includes('_')) return null;
        return key.substring(key.lastIndexOf('_') + 1);
    }

    for (const [key, value] of Object.entries(object)) {
        const type = getType(key);
        if (type) validate(type, value);
    }
    const validator = {
        construct: function (target, args) {
            const created = new target(...args);
            for (const [key, value] of Object.entries(created)) {
                const type = getType(key);
                if (type) validate(type, value);
            }
        },
        set: function (obj, prop, value) {
            const type = getType(prop);
            if (type) validate(type, value);

            validate(type, value);
            obj[prop] = value;
        }
    };

    return new Proxy(object, validator);
}


class LoginForm extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',
        };
    }

    changeValue = (attr) => (e) => {
        this.setState((state) => ({
            ...this.state,
            [attr]: e.target.value,
        }));
    };

    render() {
        const {onSubmit} = this.props;
        const {username, password} = this.state;

        return (
            <div>
                <form onSubmit={(e) => onSubmit(username, password)(e)}>
                    <input
                        id="username-input"
                        type="text"
                        name="username"
                        onChange={this.changeValue('username')}
                        value={username}
                    />
                    <input
                        id="password-input"
                        type="password"
                        name="password"
                        onChange={this.changeValue('password')}
                        value={password}
                    />
                    <button
                        id="login-button"
                        type="submit"
                        onClick={(e) => onSubmit(username, password)(e)}
                        disabled={username === '' || password === ''}
                    >
                        Submit
                    </button>
                </form>
            </div>
        );
    }
}