import React,{ Component } from "react";
import PropTypes from "prop-types";
class MyComponent extends Component{
    render()
    {
        const {name, favoriteNumber, children} = this.props;
        return (
            <div>
                안녕하세ㅔ요, 제 이름은 {name}입니다. <br/>
                children 값은 {children}
                입니다.<br/>
                제가 좋아하는 숫자는 {favoriteNumber}입니다.
            </div>
        );
    }
};
MyComponent.defaultProps={
    name:'기본 이름'
};

MyComponent.propTypes={
    name:PropTypes.string,
    favoriteNumber:PropTypes.number.isRequired
};
export default MyComponent;
