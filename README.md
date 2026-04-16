# 일정 관리 앱 - Develop

## ERD

<details>
<summary>ERD</summary>


</details>

## API 명세서

<details>
<summary>API 명세서</summary>

## 일정 관리 API

<details>
<summary>일정 CRUD</summary>

## 일정 생성 API

<details>

<summary>일정 생성 API</summary>

## 🔹 기본 정보

- **Method** : `POST`
- **URL** : `/schedules`
- **설명** : 새로운 일정을 생성합니다.

## 🔹 Request

### Headers

```text
Content-Type: application/json
```

<br>

### Body

```json
{
  "title": "점심 시간",
  "contents": "오후 1시부터 오후 2시까지 점심 시간",
  "author": "김유하"
}
```

| 필드명      | 타입     | 필수 | 설명     |
|----------|--------|----|--------|
| title    | String | O  | 할일 제목  |
| contents | String | O  | 할일 내용  |
| author   | String | O  | 작성 유저명 |

<br>

## 🔹 Response

#### ✅ 성공 - 201 Created

```json
{
  "scheduleId": 1,
  "title": "점심 시간",
  "contents": "오후 1시부터 오후 2시까지 점심 시간",
  "author": "김유하",
  "createdAt": "2026-04-18T16:30.96822",
  "updatedAt": "2026-04-18T16:30.96822"
}
```

| 필드명        | 타입            | 필수 | 설명     |
|------------|---------------|----|--------|
| scheduleId | Long          | O  | 고유 식별자 |
| title      | String        | O  | 할일 제목  |
| contents   | String        | O  | 할일 내용  |
| author     | String        | O  | 작성 유저명 |
| createdAt  | LocalDateTime | O  | 생성한 날짜 |
| updatedAt  | LocalDateTime | O  | 수정한 날짜 |

<br>

#### ❌ 실패 - 400 Bad Request

```json
{
  "message": "필수값이 입력되지 않았습니다."
}
```

</details>

## 일정 단건 조회 API

<details>
<summary>일정 단건 조회 API</summary>

## 🔹 기본 정보

- **Method** : `GET`
- **URL** : `/schedules/{scheduleId}`
- **설명** : 특정한 일정을 단건 조회합니다.

<br>

## 🔹 Path Variable

| 변수명        | 타입   | 설명     |
|------------|------|--------|
| scheduleId | Long | 고유 식별자 |

### 요청 예시

```
GET /schedules/1
```

<br>

## 🔹 Response

#### ✅ 성공 - 200 OK

```json
{
  "scheduleId": 1,
  "title": "점심 시간",
  "contents": "오후 1시부터 오후 2시까지 점심 시간",
  "author": "김유하",
  "createdAt": "2026-04-18T16:30.96822",
  "updatedAt": "2026-04-18T16:30.96822"
}
```

| 필드명        | 타입            | 필수 | 설명     |
|------------|---------------|----|--------|
| scheduleId | Long          | O  | 고유 식별자 |
| title      | String        | O  | 할일 제목  |
| contents   | String        | O  | 할일 내용  |
| author     | String        | O  | 작성 유저명 |
| createdAt  | LocalDateTime | O  | 생성한 날짜 |
| updatedAt  | LocalDateTime | O  | 수정한 날짜 |

<br>

#### ❌ 실패 - 400 Bad Request

```json
{
  "message": "필수값이 입력되지 않았습니다."
}
```

#### ❌ 실패 - 404 Not Found

```json
{
  "message": "해당 일정을 조회할 수 없습니다."
}
```

</details>

## 일정 전체 조회 API

<details>
<summary>일정 전체 조회 API</summary>

## 🔹 기본 정보

- **Method** : `GET`
- **URL** : `/schedules`
- **설명** : 전체 일정을 조회합니다.

<br>

## 🔹 Response

#### ✅ 성공 - 200 OK

```json
[
  {
    "scheduleId": 1,
    "title": "점심 시간",
    "contents": "오후 1시부터 오후 2시까지 점심 시간",
    "author": "김유하",
    "createdAt": "2026-04-18T16:30.96822",
    "updatedAt": "2026-04-18T16:30.96822"
  },
  {
    "scheduleId": 2,
    "title": "저녁 시간",
    "contents": "오후 6시부터 오후 7시까지 점심 시간",
    "author": "김유하",
    "createdAt": "2026-04-18T16:30.96822",
    "updatedAt": "2026-04-18T16:30.96822"
  }
]
```

| 필드명        | 타입            | 필수 | 설명     |
|------------|---------------|----|--------|
| scheduleId | Long          | O  | 고유 식별자 |
| title      | String        | O  | 할일 제목  |
| contents   | String        | O  | 할일 내용  |
| author     | String        | O  | 작성 유저명 |
| createdAt  | LocalDateTime | O  | 생성한 날짜 |
| updatedAt  | LocalDateTime | O  | 수정한 날짜 |

<br>

#### ❌ 실패 - 500 Not Found

```json
{
  "message": "서버 내부에서 오류가 발생했습니다."
}
```

</details>

## 일정 수정 API

<details>

<summary>일정 수정 API</summary>

## 🔹 기본 정보

- **Method** : `PATCH`
- **URL** : `/schedules/{scheduleId}`
- **설명** : 선택한 일정의 정보를 수정합니다.

<br>

## 🔹 Path Variable

| 변수명        | 타입   | 설명     |
|------------|------|--------|
| scheduleId | Long | 고유 식별자 |

### 요청 예시

```
PATCH /schedules/1
```

<br>

## 🔹 Request

### Headers

```
Content-Type: application/json
```

### Body

```json
{
  "title": "점심 시간",
  "contents": "오후 1시 반 부터 오후 2시 반 까지",
  "author": "김유하"
}
```

| 필드명      | 타입     | 필수 | 설명     |
|----------|--------|----|--------|
| title    | String | O  | 할일 제목  |
| contents | String | O  | 할일 내용  |
| author   | String | O  | 작성 유저명 |

<br>

## 🔹 Response

#### ✅ 성공 - 200 OK

```json
{
  "scheduleId": 1,
  "title": "점심 시간",
  "contents": "오후 1시 반 부터 오후 2시 반 까지",
  "author": "김유하",
  "createdAt": "2026-04-18T16:30.96822",
  "updatedAt": "2026-04-18T16:30.96822"
}
```

| 필드명        | 타입            | 필수 | 설명     |
|------------|---------------|----|--------|
| scheduleId | Long          | O  | 고유 식별자 |
| title      | String        | O  | 일정 제목  |
| contents   | String        | O  | 일정 내용  |
| author     | String        | O  | 작성자명   |
| createdAt  | LocalDateTime | O  | 생성한 날짜 |
| updatedAt  | LocalDateTime | O  | 수정한 날짜 |

#### ❌ 실패 - 400 Bad Request

```json
{
  "message": "필수값을 입력해주세요."
}
```

#### ❌ 실패 - 404 Not Found

```json
{
  "message": "일정을 찾을 수 없습니다."
}
```

</details>

## 일정 삭제 API

<details>

<summary>일정 삭제 API</summary>

## 🔹 기본 정보

- **Method** : `DELETE`
- **URL** : `/api/schedules/{scheduleId}`
- **설명** : 특정 일정을 삭제합니다.

<br>

### 🔹 Path Variable

| 변수명        | 타입   | 설명     |
|------------|------|--------|
| scheduleId | Long | 고유 식별자 |

### 요청 예시

```
DELETE /api/schedules/1
```

<br>

## 🔹 Response

#### ✅ 성공 - 204 No Content

#### ❌ 실패 - 404 Not Found

```
해당 일정을 찾을 수 없습니다!
```

</details>

</details>

<details>
<summary>유저 CRUD</summary>

## 유저 생성 API

<details>
<summary>유저 생성 API</summary>

## 🔹 기본 정보

- **Method** : `POST`
- **URL** : `/users`
- **설명** : 새로운 유저를 생성합니다.

## 🔹 Request

### Headers

```text
Content-Type: application/json
```

<br>

### Body

```json
{
  "userName": "김유하",
  "email": "devdong1231@gmail.com"
}
```

| 필드명      | 타입     | 필수 | 설명  |
|----------|--------|----|-----|
| userName | String | O  | 유저명 |
| email    | String | O  | 이메일 |

<br>

## 🔹 Response

#### ✅ 성공 - 201 Created

```json
{
  "userId": 1,
  "userName": "김유하",
  "email": "devdong1231@gmail.com",
  "createdAt": "2026-04-18T16:30.96822",
  "updatedAt": "2026-04-18T16:30.96822"
}
```

| 필드명       | 타입            | 필수 | 설명     |
|-----------|---------------|----|--------|
| userId    | Long          | O  | 고유 식별자 |
| userName  | String        | O  | 유저명    |
| email     | String        | O  | 이메일    |
| createdAt | LocalDateTime | O  | 생성한 날짜 |
| updatedAt | LocalDateTime | O  | 수정한 날짜 |

<br>

#### ❌ 실패 - 400 Bad Request

```json
{
  "message": "필수값이 입력되지 않았습니다."
}
```

</details>

## 유저 단건 조회 API

<details>
<summary>유저 단건 조회 API</summary>

## 🔹 기본 정보

- **Method** : `GET`
- **URL** : `/users/{userId}`
- **설명** : 특정한 유저을 단건 조회합니다.

<br>

## 🔹 Path Variable

| 변수명    | 타입   | 설명     |
|--------|------|--------|
| userId | Long | 고유 식별자 |

### 요청 예시

```
GET /users/1
```

<br>

## 🔹 Response

#### ✅ 성공 - 200 OK

```json
{
  "userId": 1,
  "userName": "김유하",
  "email": "devdong1231@gmail.com",
  "createdAt": "2026-04-18T16:30.96822",
  "updatedAt": "2026-04-18T16:30.96822"
}
```

| 필드명       | 타입            | 필수 | 설명     |
|-----------|---------------|----|--------|
| userId    | Long          | O  | 고유 식별자 |
| userName  | String        | O  | 유저명    |
| email     | String        | O  | 이메일    |
| createdAt | LocalDateTime | O  | 생성한 날짜 |
| updatedAt | LocalDateTime | O  | 수정한 날짜 |

<br>

#### ❌ 실패 - 400 Bad Request

```json
{
  "message": "필수값이 입력되지 않았습니다."
}
```

#### ❌ 실패 - 404 Not Found

```json
{
  "message": "해당 유저를 조회할 수 없습니다."
}
```

</details>

## 유저 전체 조회 API

<details>

<summary>유저 전체 조회 API</summary>

## 🔹 기본 정보

- **Method** : `GET`
- **URL** : `/users`
- **설명** : 전체 유저를 조회합니다.

<br>

## 🔹 Response

#### ✅ 성공 - 200 OK

```json
[
  {
    "userId": 1,
    "userName": "김유하",
    "email": "devdong1231@gmail.com",
    "createdAt": "2026-04-18T16:30.96822",
    "updatedAt": "2026-04-18T16:30.96822"
  },
  {
    "userId": 2,
    "userName": "홍길동",
    "email": "gildong1231@gmail.com",
    "createdAt": "2026-04-18T16:30.96822",
    "updatedAt": "2026-04-18T16:30.96822"
  }
]
```

| 필드명       | 타입            | 필수 | 설명     |
|-----------|---------------|----|--------|
| userId    | Long          | O  | 고유 식별자 |
| userName  | String        | O  | 유저명    |
| email     | String        | O  | 이메일    |
| createdAt | LocalDateTime | O  | 생성한 날짜 |
| updatedAt | LocalDateTime | O  | 수정한 날짜 |

<br>

#### ❌ 실패 - 500 Not Found

```json
{
  "message": "서버 내부에서 오류가 발생했습니다."
}
```

</details>

## 유저 수정 API

<details>

<summary>유저 수정 API</summary>

## 🔹 기본 정보

- **Method** : `PATCH`
- **URL** : `/users/{userId}`
- **설명** : 선택한 유저의 정보를 수정합니다.

<br>

## 🔹 Path Variable

| 필드명      | 타입     | 필수 | 설명  |
|----------|--------|----|-----|
| userName | String | O  | 유저명 |

### 요청 예시

```
PATCH /users/1
```

<br>

## 🔹 Request

### Headers

```
Content-Type: application/json
```

### Body

```json
{
  "userName": "김유하",
  "email": "devdong1231@gmail.com"
}
```

| 필드명      | 타입     | 필수 | 설명  |
|----------|--------|----|-----|
| userName | String | O  | 유저명 |
| email    | String | O  | 이메일 |

<br>

## 🔹 Response

#### ✅ 성공 - 200 OK

```json
{
  "userId": 1,
  "userName": "김유하",
  "email": "devdong1231@gmail.com",
  "createdAt": "2026-04-18T16:30.96822",
  "updatedAt": "2026-04-18T16:30.96822"
}
```

| 필드명       | 타입            | 필수 | 설명     |
|-----------|---------------|----|--------|
| userId    | Long          | O  | 고유 식별자 |
| userName  | String        | O  | 유저명    |
| email     | String        | O  | 이메일    |
| createdAt | LocalDateTime | O  | 생성한 날짜 |
| updatedAt | LocalDateTime | O  | 수정한 날짜 |

#### ❌ 실패 - 400 Bad Request

```json
{
  "message": "필수값을 입력해주세요."
}
```

#### ❌ 실패 - 404 Not Found

```json
{
  "message": "일정을 찾을 수 없습니다."
}
```

</details>

## 유저 삭제 API

<details>

<summary>유저 삭제 API</summary>

## 🔹 기본 정보

- **Method** : `DELETE`
- **URL** : `/user/{userId}`
- **설명** : 특정 유저를 삭제합니다.

<br>

### 🔹 Path Variable

| 변수명    | 타입   | 설명     |
|--------|------|--------|
| userId | Long | 고유 식별자 |

### 요청 예시

```
DELETE /api/schedules/1
```

<br>

## 🔹 Response

#### ✅ 성공 - 204 No Content

#### ❌ 실패 - 404 Not Found

```
해당 일정을 찾을 수 없습니다!
```

</details>

</details>

<details>
<summary>댓글 CRUD</summary>


</details>

</details>